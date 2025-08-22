package com.example.service1.controller;

import com.example.service1.dto.OrchestratorRequest;
import com.example.service1.dto.OrchestratorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrchestratorControllerTest {

    @Autowired
    private OrchestratorController controller;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testHealthEndpoint() {
        // When
        ResponseEntity<String> response = controller.health();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Up", response.getBody());
    }

    @Test
    void testOrchestrateEndpoint() {
        // Given
        OrchestratorRequest request = new OrchestratorRequest();
        request.setName("John");
        request.setSurname("Doe");
        String traceId = "test-trace-123";

        // Mock service2 response
        when(restTemplate.exchange(
                anyString(),
                eq(org.springframework.http.HttpMethod.GET),
                any(),
                eq(String.class)
        )).thenReturn(new ResponseEntity<>("Hello", HttpStatus.OK));

        // Mock service3 response
        when(restTemplate.exchange(
                anyString(),
                eq(org.springframework.http.HttpMethod.POST),
                any(),
                eq(String.class)
        )).thenReturn(new ResponseEntity<>("John Doe", HttpStatus.OK));

        // When
        ResponseEntity<OrchestratorResponse> response = controller.orchestrate(traceId, request);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Hello John Doe", response.getBody().getResult());
        assertEquals(traceId, response.getBody().getTraceId());
    }
}
