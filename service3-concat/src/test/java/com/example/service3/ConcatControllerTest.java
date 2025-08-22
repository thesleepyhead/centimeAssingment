package com.example.service3;

import com.example.service3.controller.ConcatController;
import com.example.service3.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ConcatController.class)
@Import(GlobalExceptionHandler.class)
class ConcatControllerTest {
    @Autowired private MockMvc mockMvc;

    @Test
    void concatReturnsFullName() throws Exception {
        String json = "{\"Name\":\"John\",\"Surname\":\"Doe\"}";
        mockMvc.perform(post("/api/v1/concat").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("John Doe"));
    }

    @Test
    void invalidJsonReturnsBadRequest() throws Exception {
        String badJson = "{\"Name\":\"John\",\"Surname\": }";
        mockMvc.perform(post("/api/v1/concat").contentType(MediaType.APPLICATION_JSON).content(badJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid JSON payload"));
    }
}
