package com.grocery.helper.controller;

import com.grocery.helper.bean.Grocery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GroceryController.class)
public class GroceryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GroceryController controller;

    @MockBean
    private Pageable pageable;


    @Test
    public void givenGroceries_whenGetAllGroceries_thenReturnJsonArray()
            throws Exception {

        Grocery grocery = Grocery.builder().id(Long.parseLong("1")).itemName("Paste").category("testing").build();
        Page<Grocery> groceryPage = new PageImpl<>(Collections.singletonList(grocery));
        given(controller.getAllGroceries(pageable)).willReturn(groceryPage);

        mvc.perform(get("/groceries")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
