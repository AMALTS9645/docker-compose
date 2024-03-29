package com.mysmartshop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mysmartshop.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{

}
