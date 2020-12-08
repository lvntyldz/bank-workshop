package com.ba.repository;

import com.ba.domain.Category;
import com.ba.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
