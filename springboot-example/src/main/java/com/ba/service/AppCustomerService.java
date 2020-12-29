package com.ba.service;

import com.ba.domain.Car;
import com.ba.domain.CarCustomer;
import com.ba.repository.CarCustomerRepository;
import com.ba.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class AppCustomerService {

    @Autowired
    private CarCustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AppCustomerServiceProxy proxy;

    public CarCustomer addNewCustomerWithoutTx() {

        CarCustomer customer = new CarCustomer(null, "Hasan HASANOĞLU", new Timestamp(System.currentTimeMillis()));
        customerRepository.save(customer);

        Car car = new Car(null, "Opel-2020", 2020, new Timestamp(System.currentTimeMillis()));

        if (true) {
            //throw new RuntimeException("Transaction rollback YAPILMAYACAK");
        }

        carRepository.save(car);

        return customer;
    }

    @Transactional
    public CarCustomer addNewCustomer() {

        CarCustomer customer = new CarCustomer(null, "Ali ALİOĞLU", new Timestamp(System.currentTimeMillis()));
        customerRepository.save(customer);

        Car car = new Car(null, "Ford-2020", 2020, new Timestamp(System.currentTimeMillis()));
        carRepository.save(car);

        if (true) {
            //throw new RuntimeException("Transaction rollback yapılacak");
        }

        return customer;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CarCustomer addNewCustomerNestedTx() {

        CarCustomer customer = new CarCustomer(null, "Veli VELİOĞLU", new Timestamp(System.currentTimeMillis()));
        customerRepository.save(customer);

        proxy.saveCar();

        if (true) {
            //throw new RuntimeException("Transaction rollback yapılacak");
        }
        return null;
    }

}
