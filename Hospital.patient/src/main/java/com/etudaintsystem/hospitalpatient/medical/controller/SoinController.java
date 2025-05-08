package com.etudaintsystem.hospitalpatient.medical.controller;

import com.etudaintsystem.hospitalpatient.medical.model.Soin;
import com.etudaintsystem.hospitalpatient.medical.service.SoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/soins")
public class SoinController {

    @Autowired
    private SoinService soinService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Soin>> getAllSoins() {
        List<Soin> soins = soinService.getAllSoins();
        return new ResponseEntity<>(soins, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Soin> getSoinById(@PathVariable Long id) {
        Optional<Soin> soin = soinService.getSoinById(id);
        return soin.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/designation/{designation}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Soin> getSoinByDesignation(@PathVariable String designation) {
        Optional<Soin> soin = soinService.getSoinByDesignation(designation);
        return soin.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Soin>> searchSoinsByDesignation(@RequestParam String designation) {
        List<Soin> soins = soinService.getSoinsByDesignationContaining(designation);
        return new ResponseEntity<>(soins, HttpStatus.OK);
    }

    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Soin>> getSoinsByPatientId(@PathVariable Long patientId) {
        List<Soin> soins = soinService.getSoinsByPatientId(patientId);
        return new ResponseEntity<>(soins, HttpStatus.OK);
    }

    @GetMapping("/statistics/frequent")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Long>> getMostFrequentSoins() {
        Map<String, Long> soinFrequency = soinService.getMostFrequentSoins();
        return new ResponseEntity<>(soinFrequency, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createSoin(@RequestBody Soin soin) {
        // Vérifier si un soin avec cette désignation existe déjà
        if (soinService.soinExistsByDesignation(soin.getDesignation())) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Un soin avec cette désignation existe déjà");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        Soin newSoin = soinService.saveSoin(soin);
        return new ResponseEntity<>(newSoin, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateSoin(@PathVariable Long id, @RequestBody Soin soinDetails) {
        try {
            Soin updatedSoin = soinService.updateSoin(id, soinDetails);
            return new ResponseEntity<>(updatedSoin, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteSoin(@PathVariable Long id) {
        try {
            soinService.deleteSoin(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Soin supprimé avec succès");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}