package com.ba.repository;

import com.ba.domain.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Page<Country> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
    //Slice<Country> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
}
