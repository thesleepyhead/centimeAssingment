package com.example.service1.controller;

import com.example.service1.dto.OrchestratorRequest;
import com.example.service1.dto.OrchestratorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1")
public class OrchestratorController {
    private static final Logger log = LoggerFactory.getLogger(OrchestratorController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service2.url:http://localhost:8081}")
    private String service2Url;

    @Value("${service3.url:http://localhost:8082}")
    private String service3Url;

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        log.info("Health check called");
        return ResponseEntity.ok("Up");
    }

    @PostMapping("/orchestrate")
    public ResponseEntity<OrchestratorResponse> orchestrate(
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId,
            @RequestBody OrchestratorRequest request) {
        
        log.info("Orchestrator called with traceId={}, request={{Name:{},Surname:{}}}", 
                traceId, request.getName(), request.getSurname());

        try {
            // Step 1: Call Service2 (GET method) - returns "Hello"
            String greeting = callService2(traceId);
            log.info("Service2 returned: {}", greeting);

            // Step 2: Call Service3 (POST method) - returns concatenated name
            String fullName = callService3(traceId, request);
            log.info("Service3 returned: {}", fullName);

            // Step 3: Combine results as per problem statement
            String result = greeting + " " + fullName;
            
            OrchestratorResponse response = new OrchestratorResponse(result, traceId);
            log.info("Orchestrator returning: {}", response.getResult());
            
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error in orchestrator: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError()
                    .body(new OrchestratorResponse("Error occurred during orchestration", traceId));
        }
    }

    private String callService2(String traceId) {
        HttpHeaders headers = new HttpHeaders();
        if (traceId != null) {
            headers.set("X-Trace-Id", traceId);
        }

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                service2Url + "/api/v1/greeting",
                HttpMethod.GET,
                entity,
                String.class
        );
        return response.getBody();
    }

    private String callService3(String traceId, OrchestratorRequest request) {
        HttpHeaders headers = new HttpHeaders();
        if (traceId != null) {
            headers.set("X-Trace-Id", traceId);
        }

        HttpEntity<OrchestratorRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                service3Url + "/api/v1/concat",
                HttpMethod.POST,
                entity,
                String.class
        );
        return response.getBody();
    }
}
