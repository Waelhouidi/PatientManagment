package com.etudaintsystem.hospitalpatient.medical.controller;

import com.etudaintsystem.hospitalpatient.medical.dto.SoinDTO;
import com.etudaintsystem.hospitalpatient.medical.service.SoinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soins")
@RequiredArgsConstructor
public class SoinController {


    private final SoinService soinService;

    @GetMapping
    public ResponseEntity<List<SoinDTO>> getAllSoins() {
        List<SoinDTO> soins = soinService.getAllSoins();
        return ResponseEntity.ok(soins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoinDTO> getSoinById(@PathVariable Long id) {
        SoinDTO soin = soinService.getSoinById(id);
        return ResponseEntity.ok(soin);
    }

    @PostMapping
    public ResponseEntity<SoinDTO> createSoin(@Valid @RequestBody SoinDTO soinDTO) {
        SoinDTO createdSoin = soinService.createSoin(soinDTO);
        return new ResponseEntity<>(createdSoin, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SoinDTO> updateSoin(
            @PathVariable Long id,
            @Valid @RequestBody SoinDTO soinDTO) {
        SoinDTO updatedSoin = soinService.updateSoin(id, soinDTO);
        return ResponseEntity.ok(updatedSoin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoin(@PathVariable Long id) {
        soinService.deleteSoin(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<SoinDTO>> searchSoins(@RequestParam String query) {
        List<SoinDTO> soins = soinService.searchSoins(query);
        return ResponseEntity.ok(soins);
    }
}