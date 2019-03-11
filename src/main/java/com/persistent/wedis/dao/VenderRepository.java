package com.persistent.wedis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.persistent.wedis.model.Vender;

@Repository
public interface VenderRepository extends JpaRepository<Vender, Long> {

	List<Vender> findBySector(String sector);

	Vender findByVenderId(Long venderId);

}
