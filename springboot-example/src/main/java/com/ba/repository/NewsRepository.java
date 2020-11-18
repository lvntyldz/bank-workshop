package com.ba.repository;

import com.ba.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT DISTINCT n.categoryName FROM News n")
    List<String> getAllUniqueCategories();

}
