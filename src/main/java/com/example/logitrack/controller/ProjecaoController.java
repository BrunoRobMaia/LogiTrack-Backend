package com.example.logitrack.controller;

import com.example.logitrack.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/projecao")
@RequiredArgsConstructor
public class ProjecaoController {

    private final DashboardService dashboardService;

    @GetMapping("/financeira")
    public ResponseEntity<Map<String, Object>> getProjecaoFinanceira() {
        return ResponseEntity.ok(Map.of("totalMes", dashboardService.getProjecaoFinanceira()));
    }
}