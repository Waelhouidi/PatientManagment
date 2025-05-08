package com.etudaintsystem.hospitalpatient.medical.service;

import com.etudaintsystem.hospitalpatient.medical.dto.SeanceDTO;
import com.etudaintsystem.hospitalpatient.medical.model.Patient;
import com.etudaintsystem.hospitalpatient.medical.model.Seance;
import com.etudaintsystem.hospitalpatient.medical.model.SeanceId;
import com.etudaintsystem.hospitalpatient.medical.model.Soin;
import com.etudaintsystem.hospitalpatient.medical.exception.ResourceNotFoundException;
import com.etudaintsystem.hospitalpatient.medical.mapper.SeanceMapper;
import com.etudaintsystem.hospitalpatient.medical.repository.PatientRepository;
import com.etudaintsystem.hospitalpatient.medical.repository.SeanceRepository;
import com.etudaintsystem.hospitalpatient.medical.repository.SoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeanceService {

    private final SeanceRepository seanceRepository;
    private final PatientRepository patientRepository;
    private final SoinRepository soinRepository;
    private final SeanceMapper seanceMapper;

    @Transactional(readOnly = true)
    public List<SeanceDTO> getAllSeances() {
        return seanceRepository.findAll().stream()
                .map(seanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SeanceDTO getSeanceById(Long patientId, Long soinId) {
        SeanceId seanceId = new SeanceId(patientId, soinId);
        Seance seance = seanceRepository.findById(seanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Séance non trouvée"));
        return seanceMapper.toDto(seance);
    }

    @Transactional
    public SeanceDTO createSeance(SeanceDTO seanceDTO) {
        Patient patient = patientRepository.findById(seanceDTO.getCodeP())
                .orElseThrow(() -> new ResourceNotFoundException("Patient non trouvé avec l'ID: " + seanceDTO.getCodeP()));

        Soin soin = soinRepository.findById(seanceDTO.getCodeSoin())
                .orElseThrow(() -> new ResourceNotFoundException("Soin non trouvé avec l'ID: " + seanceDTO.getCodeSoin()));

        SeanceId seanceId = new SeanceId(seanceDTO.getCodeP(), seanceDTO.getCodeSoin());

        Seance seance = new Seance();
        seance.setId(seanceId);
        seance.setPatient(patient);
        seance.setSoin(soin);
        seance.setDateSoin(seanceDTO.getDateSoin());

        seance = seanceRepository.save(seance);
        return seanceMapper.toDto(seance);
    }

    @Transactional
    public void deleteSeance(Long patientId, Long soinId) {
        SeanceId seanceId = new SeanceId(patientId, soinId);
        if (!seanceRepository.existsById(seanceId)) {
            throw new ResourceNotFoundException("Séance non trouvée");
        }
        seanceRepository.deleteById(seanceId);
    }

    @Transactional(readOnly = true)
    public List<SeanceDTO> getSeancesByPatient(Long patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new ResourceNotFoundException("Patient non trouvé avec l'ID: " + patientId);
        }
        return seanceRepository.findByPatientCodeP(patientId).stream()
                .map(seanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<SeanceDTO> getSeancesBySoin(Long soinId) {
        if (!soinRepository.existsById(soinId)) {
            throw new ResourceNotFoundException("Soin non trouvé avec l'ID: " + soinId);
        }
        return seanceRepository.findBySoinCodeSoin(soinId).stream()
                .map(seanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<SeanceDTO> getSeancesByDateRange(LocalDate dateDebut, LocalDate dateFin) {
        return seanceRepository.findByDateSoinBetween(dateDebut, dateFin).stream()
                .map(seanceMapper::toDto)
                .collect(Collectors.toList());
    }
}