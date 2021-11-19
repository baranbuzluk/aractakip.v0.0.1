package com.ldselektronik.window.carregistration.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldselektronik.window.carregistration.data.entity.CarBrandEntity;
import com.ldselektronik.window.carregistration.data.repository.CarBrandRepository;

/**
 * 
 * @author Baran
 *
 */
@Service(value = "carBrandService")
@Transactional
public class CarBrandService {

	@Autowired
	private CarBrandRepository repository;

	@Autowired
	private Logger logger;

	public List<CarBrandEntity> getAllCarBrands() {
		return repository.findAll();

	}

	/**
	 * If there is the object has same <code>name</code> value in the table, The
	 * object is not saved to the table.
	 * 
	 */
	public void save(CarBrandEntity entity) {
		if (entity == null) {
			logger.log(Level.WARNING, "Error CarBrand object is null!");
			return;
		}
		if (repository.existsByName(entity.getName()) || entity.getName().isEmpty() || entity.getName() == null)
			return;
		repository.save(entity);
	}

}
