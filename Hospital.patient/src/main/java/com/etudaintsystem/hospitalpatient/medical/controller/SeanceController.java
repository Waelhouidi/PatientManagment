package com.etudaintsystem.hospitalpatient.medical.controller;

import com.etudaintsystem.hospitalpatient.medical.dto.SeanceDTO;
import com.etudaintsystem.hospitalpatient.medical.service.SeanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/seances")
@RequiredArgsConstructor
public class SeanceController {

    private final SeanceService seanceService;

    @GetMapping
    public ResponseEntity<List<SeanceDTO>> getAllSeances() {
        List<SeanceDTO> seances = seanceService.getAllSeances();
        return ResponseEntity.ok(seances);
    }

    @GetMapping("/{patientId}/{soinId}")
    public ResponseEntity<SeanceDTO> getSeanceById(
            @PathVariable Long patientId,
            @PathVariable Long soinId) {
        SeanceDTO seance = seanceService.getSeanceById(patientId, soinId);
        return ResponseEntity.ok(seance);
    }

    @PostMapping
    public ResponseEntity<SeanceDTO> createSeance(@Valid @RequestBody SeanceDTO seanceDTO) {
        SeanceDTO createdSeance = seanceService.createSeance(seanceDTO);
        return new ResponseEntity<>(createdSeance, HttpStatus.CREATED);
    }

    @DeleteMapping("/{patientId}/{soinId}")
    public ResponseEntity<Void> deleteSeance(
            @PathVariable Long patientId,
            @PathVariable Long soinId) {
        seanceService.deleteSeance(patientId, soinId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<SeanceDTO>> getSeancesByPatient(@PathVariable Long patientId) {
        List<SeanceDTO> seances = seanceService.getSeancesByPatient(patientId);
        return ResponseEntity.ok(seances);
    }

    @GetMapping("/soin/{soinId}")
    public ResponseEntity<List<SeanceDTO>> getSeancesBySoin(@PathVariable Long soinId) {
        List<SeanceDTO> seances = seanceService.getSeancesBySoin(soinId);
        return ResponseEntity.ok(seances);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<SeanceDTO>> getSeancesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
        List<SeanceDTO> seances = seanceService.getSeancesByDateRange(dateDebut, dateFin);
        return ResponseEntity.ok(seances);
    }
}