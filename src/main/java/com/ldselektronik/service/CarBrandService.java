package com.ldselektronik.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldselektronik.dto.CarBrandDTO;
import com.ldselektronik.dto.converter.Converter;
import com.ldselektronik.model.CarBrand;
import com.ldselektronik.repository.CarBrandRepository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Baran
 *
 */
@Service
@Transactional
public class CarBrandService {

	@Autowired
	private CarBrandRepository repository;

	public ObservableList<CarBrandDTO> getAll() {
		List<CarBrand> brandList = repository.findAll();
		// Converts CarBrand object to CarBrandDTO object
		List<CarBrandDTO> brandDTOList = brandList.stream().map(brand -> Converter.toCarBrandDTO(brand)) 
				.collect(Collectors.toList());
		return FXCollections.observableArrayList(brandDTOList);
	}

	/**
	 * If there is the object has same <code>name</code> value in the table, The
	 * object is not saved to the table.
	 * 
	 */
	public void save(CarBrandDTO brand) {

		if (brand == null) {
			System.out.println("Error CarBrand object is null!");
			return;
		}

		if (repository.existsByName(brand.getName()) || brand.getName().isEmpty() || brand.getName() == null)
			return;

		repository.save(Converter.toCarBrand(brand));
	}

}
