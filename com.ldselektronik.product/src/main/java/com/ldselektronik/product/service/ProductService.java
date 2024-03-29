package com.ldselektronik.product.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ldselektronik.product.data.entity.Product;
import com.ldselektronik.product.repository.ProductRepository;

/**
 * @author Baran
 *
 */
@Service
@Transactional
public class ProductService {

	@Autowired
	ProductRepository repository;

	@Autowired
	Logger logger;

	public List<Product> getAllProductEntities() {
		return repository.findAll();

	}

	/**
	 * If there is the object has same <code>id</code> value in the table, The
	 * object is not saved to the table but is updated.
	 * 
	 */
	public void saveEntity(Product entity) {
		if (entity == null) {
			logger.log(Level.WARNING, "Error ProductEntiy object is null! - The object was not saved.");
			return;
		}
		repository.save(entity);
	}

	/**
	 * @param id - primary key
	 * @return ProductEntiy<br>
	 *         <code>null</code> - if given id has not
	 */
	public Product findEntityById(int id) {
		Optional<Product> optional = repository.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

}
