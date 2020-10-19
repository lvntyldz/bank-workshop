package com.ba.service;

import com.ba.domain.Customer;
import com.ba.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Cacheable(value = "CustomerCache", key = "'CUSTOMER_CAHE_BY_ID_'.concat(#id)", condition = "#id >= 5")
    public Customer getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (!optionalCustomer.isPresent()) {
            return null;
        }

        return optionalCustomer.get();
    }

    @CacheEvict(value = "CustomerCache", key = "'CUSTOMER_CAHE_BY_ID_'.concat(#id)", condition = "#id >= 5")
    public String deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
        return "ID : " + id + " olan içerik silindi";
    }

    @CachePut(value = "CustomerCache", key = "'CUSTOMER_CAHE_BY_ID_'.concat(#id)", condition = "#id >= 5")
    public Customer uodateCustomerNameById(Long id, String newName) {
        Customer customer = getCustomerByGivenId(id);
        customer.setName(newName);
        customerRepository.save(customer);
        return customer;
    }

    private Customer getCustomerByGivenId(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (!optionalCustomer.isPresent()) {
            return null;
        }

        return optionalCustomer.get();
    }

    @CacheEvict(value = "CustomerCache", allEntries = true)
    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }
}
