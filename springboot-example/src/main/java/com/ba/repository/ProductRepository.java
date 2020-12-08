package com.ba.repository;

import com.ba.domain.Product;
import com.ba.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
