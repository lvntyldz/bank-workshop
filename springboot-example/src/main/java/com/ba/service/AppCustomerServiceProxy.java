package com.ba.service;

import com.ba.domain.Car;
import com.ba.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class AppCustomerServiceProxy {

    @Autowired
    private CarRepository carRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveCar() {
        Car car = new Car(null,"Bmw-2020",2020,new Timestamp(System.currentTimeMillis()));
        carRepository.save(car);

    }
}
