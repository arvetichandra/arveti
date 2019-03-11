package com.persistent.wedis.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.persistent.wedis.dao.UserRepository;

@Service // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.debug("Username:{}", username);

		com.persistent.wedis.model.User user = userRepo.findByLoginId(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));

		log.debug("user:{}, password:{}", user.getLoginId(), encoder.encode(user.getPassword()));

		// Remember that Spring needs roles to be in this format: "ROLE_" + userRole
		// (i.e. "ROLE_ADMIN")
		// So, we need to set it to that format, so we can verify and compare roles
		// (i.e. hasRole("ADMIN")).
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());

		// The "User" class is provided by Spring and represents a model class for user
		// to be returned by UserDetailsService
		// And used by auth manager to verify and check user authentication.
		return new User(user.getLoginId(), encoder.encode(user.getPassword()), grantedAuthorities);

	}

}