package com.persistent.wedis.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.wedis.enums.AuthRequestStatus;
import com.persistent.wedis.model.AuthRequest;
import com.persistent.wedis.model.User;
import com.persistent.wedis.service.AuthRequestService;
import com.persistent.wedis.service.UserService;
import com.persistent.wedis.service.VenderService;

/**
 * 
 * @author chandra_areti
 *
 */
@RestController
@RequestMapping("/authRequest")
public class AuthRequestController {

	@Autowired
	AuthRequestService authRequestService;

	@Autowired
	UserService userService;

	@Autowired
	VenderService venderService;

	@GetMapping("/code/{code}")
	public @ResponseBody Map<String, Object> getCodeRelatedData(Authentication authentication,
			@PathVariable String code) {
		String userName = authentication.getName();
		User user = userService.getUserByUsername(userName);
		return authRequestService.getCodeRelatedData(code, user);
	}

	@PostMapping("/create")
	public ResponseEntity<Map<String, String>> creatAuthRequest(Authentication authentication,
			@RequestBody AuthRequest authRequest) {
		String userName = authentication.getName();
		User user = userService.getUserByUsername(userName);
		authRequest.setUserId(user.getUserId());
		System.out.println(user.getUserId());

		authRequest.setStatusCode(AuthRequestStatus.SHARED_ATTRIBUTES.getAuthRequestStatus());

		Map<String, String> result = new HashMap<String, String>();
		if (authRequest != null && authRequest.getSharedAttributes() != null) {
			String[] sharedAttributes = authRequest.getSharedAttributes().split(",");
			String[] originalAttributes = { "name", "email", "dob", "phone", "gender", "age", "photo" };
			for (String attribute : sharedAttributes) {
				if (Arrays.asList(originalAttributes).contains(attribute)) {

				} else {
					result.put("result", "error");
					return ResponseEntity.status(HttpStatus.OK).body(result);
				}
			}
		} else {
			result.put("result", "error");
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}

		result.put("code", authRequestService.updateAuthRequest(authRequest).getUniqueAuthCode());

		return ResponseEntity.status(HttpStatus.CREATED).body(result);

	}
}
