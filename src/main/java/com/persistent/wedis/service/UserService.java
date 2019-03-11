package com.persistent.wedis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.persistent.wedis.model.User;

@Service
public interface UserService {

	List<User> getUserAttributes();

	User getUserByUsername(String userName);

	User updateUser(User user);

}
