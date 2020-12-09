package com.ba.controller;

import com.ba.domain.Category;
import com.ba.domain.Product;
import com.ba.repository.CategoryRepository;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/add")
    public String addCategory() {

        Product product1 = productRepository.findById(1L).get();
        Product product2 = productRepository.findById(3L).get();
        Product product3 = productRepository.findById(5L).get();

        Category category = new Category();
        category.setName("Fırsat");
        category.setDescription("fırsatlar kategorisi");
        category.setProducts(Stream.of(product1, product2, product3).collect(Collectors.toSet()));

        Category savedCategory = categoryRepository.save(category);

        return savedCategory.toString();
    }

    @GetMapping("/add-products")
    public String addProducts() {

        Category category1 = categoryRepository.findById(1L).get();
        Category category2 = categoryRepository.findById(2L).get();

        Product product = new Product();
        product.setName("Kotlin");
        product.setPrice(100);
        product.setCategories(Stream.of(category1,category2).collect(Collectors.toSet()));

        category1.getProducts().add(product);
        category2.getProducts().add(product);

        Product savedProduct = productRepository.save(product);

        return savedProduct.toString();
    }

    @GetMapping("/list")
    public List<Category> getAllCategory() {

        return categoryRepository.findAll();
    }

    @GetMapping("/list-products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.isPresent() ? optionalCategory.get() : null;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return "içerik silindi. id : " + id;
    }
}
