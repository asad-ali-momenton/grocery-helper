package com.grocery.helper.repository;

import com.grocery.helper.bean.Grocery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource
public interface GroceryRepository extends JpaRepository<Grocery, Long> {
    Page<Grocery> findByCategory(String category, Pageable pageable);
}
