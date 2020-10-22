package com.ba.service;

import com.ba.domain.Person;
import com.ba.dto.BusinessRuleException;
import com.ba.exception.SystemException;
import com.ba.helper.RandomHelper;
import com.ba.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public String addNewPerson() {
        Person person = new Person(null, false, "Ali", "Alioğlu", RandomHelper.generateByMinAndMax(0,200));
        //person.setId(100L);//for throe exception

        if (person.getId() != null) {
            throw new SystemException("Person ID null olmaldır!");
        }

        if (person.getAge() > 100 || person.getAge() < 0) {
            throw new BusinessRuleException("Girilen yaş 0-100 aralığında olmaldıır!");
        }

        personRepository.save(person);

        return person.toString();
    }

    public String deletePersonById(Long id) {

        if (id <= 0) {
            throw new BusinessRuleException("ID sıfırdan büyük olmalıdır!");
        }

        personRepository.deleteById(id);

        return "ID : " + id + " olan içerik silindi";
    }
}
