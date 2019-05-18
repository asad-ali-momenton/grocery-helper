package com.grocery.helper.repository;

import com.grocery.helper.bean.Grocery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GroceryRepositoryTest {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private GroceryRepository groceryRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(entityManager).isNotNull();
        assertThat(groceryRepository).isNotNull();
    }


    @Test
    public void testFindById() {
        Optional<Grocery> grocery = groceryRepository.findById(Long.parseLong("1"));
        assertThat(grocery).isNotNull();
    }
}
