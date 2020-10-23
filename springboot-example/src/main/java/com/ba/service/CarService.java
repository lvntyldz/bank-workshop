package com.ba.service;

import com.ba.domain.Car;
import com.ba.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    @Cacheable(cacheNames = "CarCache")
    public Car getCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (!optionalCar.isPresent()) {
            return null;
        }

        return optionalCar.get();
    }

    @CacheEvict(cacheNames = "CarCache")
    public String deleteCarById(Long id) {
        carRepository.deleteById(id);
        return "ID : " + id + " olan içerik silindi";
    }

}
