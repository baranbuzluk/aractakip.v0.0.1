package com.ldselektronik.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ldselektronik.product.data.entity.ProductCategory;
import com.ldselektronik.product.repository.ProductCategoryRepository;

/**
 * @author Baran
 *
 */
@Service
@Transactional
public class ProductCategoryService {

	@Autowired
	ProductCategoryRepository repository;

	public List<ProductCategory> getAllProductCategoriesEntities() {
		return repository.findAll();
	}
}
