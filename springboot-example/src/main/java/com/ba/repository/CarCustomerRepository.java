package com.ba.repository;

import com.ba.domain.CarCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarCustomerRepository extends JpaRepository<CarCustomer, Long> {
}
