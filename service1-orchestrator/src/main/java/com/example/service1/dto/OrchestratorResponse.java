package com.example.service1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrchestratorResponse {
    @JsonProperty("Result")
    private String result;

    @JsonProperty("TraceId")
    private String traceId;

    public OrchestratorResponse() {}

    public OrchestratorResponse(String result, String traceId) {
        this.result = result;
        this.traceId = traceId;
    }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    
    public String getTraceId() { return traceId; }
    public void setTraceId(String traceId) { this.traceId = traceId; }
}
