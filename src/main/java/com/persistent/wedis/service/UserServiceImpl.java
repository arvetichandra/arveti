package com.persistent.wedis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistent.wedis.dao.UserRepository;
import com.persistent.wedis.exception.ResourceNotFoundException;
import com.persistent.wedis.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> getUserAttributes() {
		return userRepo.findAll();
	}

	@Override
	public User getUserByUsername(String userName) {
		return userRepo.findByLoginId(userName).orElseThrow(() -> new ResourceNotFoundException("Username not found"));
	}

	@Override
	public User updateUser(User user) {
		return userRepo.save(user);
	}

}
