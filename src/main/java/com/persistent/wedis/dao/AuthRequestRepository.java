package com.persistent.wedis.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.persistent.wedis.model.AuthRequest;

public interface AuthRequestRepository extends JpaRepository<AuthRequest, Integer> {
	AuthRequest findByAuthId(Integer authId);

	Optional<AuthRequest> findByUniqueAuthCode(String uniqueAuthCode);

}
