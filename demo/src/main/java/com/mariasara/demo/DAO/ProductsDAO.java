package com.mariasara.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mariasara.demo.entity.Product;

public interface ProductsDAO extends JpaRepository<Product,Long>{

}
