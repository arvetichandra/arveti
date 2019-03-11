package com.persistent.wedis.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static Logger log = LoggerFactory.getLogger(JwtUsernameAndPasswordAuthenticationFilter.class);

	// We use auth manager to validate the user credentials
	private AuthenticationManager authManager;

	private final JwtConfig jwtConfig;

	public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager, JwtConfig jwtConfig) {
		this.authManager = authManager;
		this.jwtConfig = jwtConfig;

		// By default, UsernamePasswordAuthenticationFilter listens to "/login" path.
		// In our case, we use "/auth". So, we need to override the defaults.
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri(), "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {

			// 1. Get credentials from request
			UserCredentials creds = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);

			// 2. Create auth object (contains credentials) which will be used by auth
			// manager
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getUsername(),
					creds.getPassword(), Collections.emptyList());

			log.debug("UserCredentials:{}", creds);
			// 3. Authentication manager authenticate the user, and use
			// UserDetialsServiceImpl::loadUserByUsername() method to load the user.
			return authManager.authenticate(authToken);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// Upon successful authentication, generate a token.
	// The 'auth' passed to successfulAuthentication() is the current authenticated
	// user.
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		Long now = System.currentTimeMillis();
		List<String> roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		String token = Jwts.builder().setSubject(auth.getName())
				// Convert to list of strings.
				// This is important because it affects the way we get them back in the Gateway.
				.claim("authorities", roles).setIssuedAt(new Date(now))
				.setExpiration(new Date(now + jwtConfig.getExpiration() * 1000)) // in milliseconds
				.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes()).compact();

		JSONObject json = new JSONObject();
		try {
			json.put("result", true);
			json.put(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
			json.put("roles", String.join(",", roles));
		} catch (JSONException e) {
			log.error("Erorr occured", e.getMessage());
		}
		response.getWriter().write(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	// A (temporary) class just to represent the user credentials
	private static class UserCredentials {
		private String username, password;

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}

		@Override
		public String toString() {
			return "UserCredentials [username=" + username + ", password=" + password + "]";
		}

	}
}