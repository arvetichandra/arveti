package com.persistent.wedis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.wedis.model.Vender;
import com.persistent.wedis.service.VenderService;

/**
 * 
 * @author chandra_areti
 *
 */

@RestController
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	private VenderService venderService;

	@GetMapping("/data/getPoSName/{sector}")
	public List<Vender> getAllVender(@PathVariable String sector) {
		return venderService.getAllVender(sector);
	}

	@GetMapping("/details/{id}")
	public Vender vendorDetails(@PathVariable Long id) {
		return venderService.vendorDetails(id);
	}

}
