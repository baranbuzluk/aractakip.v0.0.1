package com.ldselektronik.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldselektronik.data.model.CarRegistration;
import com.ldselektronik.data.repository.CarRegistrationRepository;
import com.ldselektronik.service.dto.CarRegistrationDTO;
import com.ldselektronik.service.dto.converter.Converter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Service
@Transactional
public class CarRegistrationService {

	@Autowired
	private CarRegistrationRepository repository;

	@Autowired
	private Logger logger;

	public ObservableList<CarRegistrationDTO> getAll() {
		List<CarRegistration> registrationList = repository.findAll();
		List<CarRegistrationDTO> registrationDTOList = registrationList.stream()
				// Converts CarRegistration object to CarRegistrationDTO object
				.map(registration -> Converter.toCarRegistrationDTO(registration)).collect(Collectors.toList());
		return FXCollections.observableArrayList(registrationDTOList);
	}

	/**
	 * If there is the object has same <code>documentNo</code> value in the table,
	 * The object is not saved to the table but is updated.
	 * 
	 */
	public void save(CarRegistrationDTO registration) {
		if (registration == null) {
			logger.log(Level.WARNING, "Error CarRegistrationDTO object is null!");
			return;
		}
		if (repository.existsByDocumentNo(registration.getDocumentNo())) {
			Integer id = repository.findByDocumentNo(registration.getDocumentNo()).getId();
			registration.setId(id);
		}
		repository.save(Converter.toCarRegistration(registration));
	}
	/**
	 * 
	 * @return <code>null</code> - if id is not found 
	 *    */
	public CarRegistrationDTO findById(int id) {
		Optional<CarRegistration> optional = repository.findById(id);
		return optional.isPresent() ? Converter.toCarRegistrationDTO(optional.get()) : null;
	}
}