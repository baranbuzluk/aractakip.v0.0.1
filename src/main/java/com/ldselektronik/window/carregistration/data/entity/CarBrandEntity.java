package com.ldselektronik.window.carregistration.data.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * Araba markalarının saklanacağı tablodur.,
 * 
 * @author Baran
 */
@Entity(name = "car_brand")
public class CarBrandEntity implements Serializable {

	private static final long serialVersionUID = -8325590200537745924L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@OneToMany(mappedBy = "carBrand",targetEntity = CarRegistrationEntity.class)
	private List<CarRegistrationEntity> carRegistrationList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CarRegistrationEntity> getCarRegistrationList() {
		return carRegistrationList;
	}
}