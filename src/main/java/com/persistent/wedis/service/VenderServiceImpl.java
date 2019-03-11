package com.persistent.wedis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistent.wedis.dao.VenderRepository;
import com.persistent.wedis.model.Vender;

/**
 * 
 * @author chandra_areti
 *
 */
@Service
public class VenderServiceImpl implements VenderService {

	@Autowired
	VenderRepository venderRepo;

	@Override
	public List<Vender> getAllVender(String sector) {
		return venderRepo.findBySector(sector);
	}

	@Override
	public Vender vendorDetails(Long id) {
		return venderRepo.findOne(id);
	}

	public Vender updateVender(Vender vender) {
		return venderRepo.save(vender);
	}

	@Override
	public Vender findByVenderId(Long venderCode) {
		return venderRepo.findByVenderId(venderCode);
	}

}
