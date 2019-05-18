package com.cnspringmvc.demo.service;


import com.cnspringmvc.demo.domain.Product;

public interface ProductService {
	Product add(Product product);
	Product get(long id);
}
