package com.persistent.wedis.service;

import java.util.List;

import com.persistent.wedis.model.Vender;

/**
 * 
 * @author chandra_areti
 *
 */
public interface VenderService {

	List<Vender> getAllVender(String sector);

	Vender vendorDetails(Long id);

	Vender updateVender(Vender vender);

	Vender findByVenderId(Long venderCode);

}
