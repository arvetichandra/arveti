package com.persistent.wedis.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.persistent.wedis.dao.AuthRequestRepository;
import com.persistent.wedis.dao.UserRepository;
import com.persistent.wedis.exception.ResourceNotFoundException;
import com.persistent.wedis.model.AuthRequest;
import com.persistent.wedis.model.User;

@Service
public class AuthRequestServiceImpl implements AuthRequestService {

	@Autowired
	AuthRequestRepository authRequestRepo;
	@Autowired
	UserRepository userRepository;

	@Override
	public AuthRequest createAuthRequest(AuthRequest authRequest) {

		return authRequestRepo.save(authRequest);
	}

	@Override
	public AuthRequest findByAuthId(Integer authId) {
		return authRequestRepo.findOne(authId);
	}

	@Override
	public AuthRequest updateAuthRequest(AuthRequest authRequest) {
		saveCodeGenereated(authRequest);
		return authRequestRepo.save(authRequest);
	}

	@Override
	public Map<String, Object> getCodeRelatedData(String code, User user1) {

		AuthRequest authRequest = authRequestRepo.findByUniqueAuthCode(code)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Code"));

		if (!authRequest.getVenderCode().equals(user1.getVenderCode())) {
			throw new ResourceAccessException("You are not intend VENDOR");
		}

		String sharedAttribute = null;
		if (authRequest.getSharedAttributes() != null) {
			sharedAttribute = authRequest.getSharedAttributes();
		}
		User user = userRepository.findOne(authRequest.getUserId());
		Map map = new LinkedHashMap<String, String>();
		if (user != null && sharedAttribute != null) {
			String[] sharedAttributes = sharedAttribute.split(",");
			boolean isAgeGreaterOrLess25 = false;
			if (user.getAge() < 25) {
				isAgeGreaterOrLess25 = false;
			} else if (user.getAge() >= 25) {
				isAgeGreaterOrLess25 = true;
			}
			for (String attribute : sharedAttributes) {
				switch (attribute) {
				case "name":
					map.put("name", user.getName());
					break;
				case "DOB":
					map.put("DOB", user.getDob());
					break;
				case "address":
					map.put("address", user.getAddress());
					break;
				case "email_id":
					map.put("email_id", user.getEmail());
					break;
				case "mobile_number":
					map.put("mobile_number", user.getMobileNumber());
					break;
				case "gender":
					map.put("gender", user.getGender());
					break;
				case "city":
					map.put("city", user.getCity());
					break;
				case "pincode":
					map.put("pincode", user.getPincode());
					break;
				case "home_phone":
					map.put("home_phone", user.getHomePhone());
					break;
				case "age":
					map.put("age", user.getAge());
					break;
				default:
					map.put("name", user.getName());
					map.put("age", user.getAge());
					map.put("isAgeGreaterOrLess25", isAgeGreaterOrLess25);
					break;
				}
			}
		}
		return map;
	}

	private void saveCodeGenereated(AuthRequest authRequest) {
		String code = getAlphaNumericString();
		authRequest.setUniqueAuthCode(code);
	}

	private String getAlphaNumericString() {
		// n is no.of character length
		int n = 15;
		// lower limit for LowerCase Letters
		int lowerLimit = 97;

		// upper limit for LowerCase Letters
		int upperLimit = 122;

		Random random = new Random();

		// Create a StringBuffer to store the result
		StringBuffer r = new StringBuffer(n);

		for (int i = 0; i < n; i++) {

			// take a random value between 97 and 122
			int nextRandomChar = lowerLimit + (int) (random.nextFloat() * (upperLimit - lowerLimit + 1));

			// append a character at the end of bs
			r.append((char) nextRandomChar);
		}

		// return the resultant string
		return r.toString();
	}

}
