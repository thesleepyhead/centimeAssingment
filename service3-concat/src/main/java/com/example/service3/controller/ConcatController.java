package com.example.service3.controller;

import com.example.service3.dto.NamePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ConcatController {
    private static final Logger log = LoggerFactory.getLogger(ConcatController.class);

    @PostMapping("/concat")
    public ResponseEntity<String> concat(
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId,
            @RequestBody NamePayload payload) {
        log.info("Service3 /concat called, traceId={}, payload={{Name:{},Surname:{}}}",
                traceId, payload != null ? payload.getName() : null, payload != null ? payload.getSurname() : null);
        String first = payload.getName() != null ? payload.getName().trim() : "";
        String last = payload.getSurname() != null ? payload.getSurname().trim() : "";
        String fullName = (first + " " + last).trim();
        return ResponseEntity.ok(fullName);
    }
}
