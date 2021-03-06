package com.grocery.helper.controller;

import com.grocery.helper.bean.Grocery;
import com.grocery.helper.repository.GroceryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class GroceryController {

    @Autowired
    private GroceryRepository groceryRepository;

    /**
     * Get All Grocery Items. Only created for completeness. DO NOT call in production and expose this method.
     *
     * @param pageable pageable object
     * @return Page with Grocery result
     */
    @GetMapping("/all-groceries")
    public Page<Grocery> getAllGroceries(Pageable pageable) {
        return groceryRepository.findAll(pageable);
    }

    /**
     * Find grocery items by the defined Category.
     *
     * @param category category to search for.
     * @param pageable pageable object
     * @return All Grocery objects with the categroy defined as the supllied request parameter
     */
    @GetMapping("/groceries")
    public Page<Grocery> findByCategory(@RequestParam(name = "category", required = false) String category, Pageable pageable) {
        if (category != null && category.trim().length() > 0)
            return groceryRepository.findByCategory(category, pageable);

        return groceryRepository.findAll(pageable);
    }

    /**
     * Find grocery items by the defined id.
     *
     * @param groceryId groceryId to search for.
     * @return All Grocery objects with the categroy defined as the supllied request parameter
     */
    @GetMapping("/groceries/{groceryId}")
    public Grocery findByCategory(@PathVariable Long groceryId) {
        return groceryRepository.findById(groceryId).get();
    }

    /**
     * Insert a new Grocery object.
     *
     * @param post posted object containing the Grocery data
     * @return the serialized object
     */
    @PostMapping("/groceries")
    public Grocery createPost(@Valid @RequestBody Grocery post) {
        return groceryRepository.save(post);
    }

    /**
     * Update existing Grocery object with the supplied data.
     *
     * @param postRequest posted object with the Grocery data
     * @return the updated object
     */
    @PutMapping("/groceries")
    public Grocery updatePost(@Valid @RequestBody Grocery postRequest) {
        return groceryRepository.findById(postRequest.getId()).map(grocery -> {

            grocery.setItemName(postRequest.getItemName());
            grocery.setCategory(postRequest.getCategory());

            return groceryRepository.save(grocery);
        }).orElseThrow(() -> new ResourceNotFoundException("Id " + postRequest.getId() + " not found"));
    }

    /**
     * Delete a Grocery object.
     *
     * @param groceryId Id for the grocery object
     * @return
     */
    @DeleteMapping("/groceries/{groceryId}")
    public ResponseEntity<?> deletePost(@PathVariable Long groceryId) {
        return groceryRepository.findById(groceryId).map(post -> {
            groceryRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Id " + groceryId + " not found"));
    }
}