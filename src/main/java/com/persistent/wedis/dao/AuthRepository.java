/**
 * 
 */
package com.persistent.wedis.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.persistent.wedis.model.AuthRequest;

/**
 * @author rajendra_k
 *
 */
@Repository
public interface AuthRepository extends JpaRepository<AuthRequest, Long> {

}
