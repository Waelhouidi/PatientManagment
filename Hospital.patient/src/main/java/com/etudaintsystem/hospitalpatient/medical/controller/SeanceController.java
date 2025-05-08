package com.etudaintsystem.hospitalpatient.medical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.etudaintsystem.hospitalpatient.medical.service.SeanceService;

/**
 * Controller for managing seances (treatment sessions).
 *
 * Note: This is a skeleton implementation since SeanceService is empty.
 * The methods will need to be updated once the SeanceService is implemented.
 */
@RestController
@RequestMapping("/api/seances")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SeanceController {

    @Autowired
    private SeanceService seanceService;

    @GetMapping
    public ResponseEntity<?> getAllSeances() {
        // To be implemented when SeanceService is available
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSeanceById(@PathVariable Long id) {
        // To be implemented when SeanceService is available
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getSeancesByPatient(@PathVariable Long patientId) {
        // To be implemented when SeanceService is available
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/soin/{soinId}")
    public ResponseEntity<?> getSeancesBySoin(@PathVariable Long soinId) {
        // To be implemented when SeanceService is available
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping
    public ResponseEntity<?> createSeance(@RequestBody Object seanceRequest) {
        // To be implemented when SeanceService is available
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSeance(@PathVariable Long id, @RequestBody Object seanceRequest) {
        // To be implemented when SeanceService is available
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeance(@PathVariable Long id) {
        // To be implemented when SeanceService is available
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}