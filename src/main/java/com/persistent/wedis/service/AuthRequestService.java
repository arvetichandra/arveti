package com.persistent.wedis.service;

import java.util.Map;

import com.persistent.wedis.model.AuthRequest;
import com.persistent.wedis.model.User;

/**
 * 
 * @author chandra_areti
 *
 */
public interface AuthRequestService {

	AuthRequest createAuthRequest(AuthRequest authRequest);

	AuthRequest updateAuthRequest(AuthRequest authRequest);

	AuthRequest findByAuthId(Integer authId);

	Map<String, Object> getCodeRelatedData(String code, User user);

	// void saveCodeGenereated();
}
