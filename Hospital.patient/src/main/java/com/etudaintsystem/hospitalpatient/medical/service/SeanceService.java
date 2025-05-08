package com.etudaintsystem.hospitalpatient.medical.service;

import com.etudaintsystem.hospitalpatient.medical.model.Seance;
import com.etudaintsystem.hospitalpatient.medical.model.SeanceId;
import com.etudaintsystem.hospitalpatient.medical.repository.SeanceRepository;
import com.etudaintsystem.hospitalpatient.medical.repository.PatientRepository;
import com.etudaintsystem.hospitalpatient.medical.repository.SoinRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class SeanceService {

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private SoinRepository soinRepository;

    @Transactional(readOnly = true)
    public List<Seance> getAllSeances() {
        return seanceRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Seance> getSeanceById(SeanceId id) {
        return seanceRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Seance> getSeancesByPatientId(Long patientId) {
        return seanceRepository.findByPatientCodeP(patientId);
    }

    @Transactional(readOnly = true)
    public List<Seance> getSeancesBySoinId(Long soinId) {
        return seanceRepository.findBySoinCodeSoin(soinId);
    }

    @Transactional(readOnly = true)
    public List<Seance> getSeancesByDate(LocalDate date) {
        return seanceRepository.findByDateSoin(date);
    }

    @Transactional(readOnly = true)
    public List<Seance> getSeancesByDateRange(LocalDate startDate, LocalDate endDate) {
        return seanceRepository.findByDateSoinBetween(startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<Seance> getSeancesByPatientAndDateRange(Long patientId, LocalDate startDate, LocalDate endDate) {
        return seanceRepository.findByPatientAndDateBetween(patientId, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<Seance> getSeancesByPatientAndSoin(Long patientId, Long soinId) {
        return seanceRepository.findByPatientCodePAndSoinCodeSoin(patientId, soinId);
    }

    @Transactional(readOnly = true)
    public boolean checkPatientSoinConflict(Long patientId, Long soinId, LocalDate date, String heure) {
        return seanceRepository.existsByPatientCodePAndSoinCodeSoinAndDateSoinAndHeure(patientId, soinId, date, heure);
    }

    @Transactional
    public Seance saveSeance(Seance seance) {
        if (seance.getPatient() == null || seance.getPatient().getCodeP() == null) {
            throw new RuntimeException("Patient est requis pour la séance.");
        }
        if (seance.getSoin() == null || seance.getSoin().getCodeSoin() == null) {
            throw new RuntimeException("Soin est requis pour la séance.");
        }

        patientRepository.findById(seance.getPatient().getCodeP())
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id: " + seance.getPatient().getCodeP()));

        soinRepository.findById(seance.getSoin().getCodeSoin())
                .orElseThrow(() -> new RuntimeException("Soin non trouvé avec l'id: " + seance.getSoin().getCodeSoin()));

        if (seance.getDateSoin() == null) {
            seance.setDateSoin(LocalDate.now());
        }

        // Mise à jour de l'ID composite
        seance.setId(new SeanceId(seance.getPatient().getCodeP(), seance.getSoin().getCodeSoin(), seance.getDateSoin()));

        return seanceRepository.save(seance);
    }

    @Transactional
    public Seance updateSeance(SeanceId id, Seance seanceDetails) {
        Seance seance = seanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Séance non trouvée avec l'id: " + id));

        seance.setDateSoin(seanceDetails.getDateSoin());
        /*
        seance.setHeure(seanceDetails.getHeure());
        seance.setDuree(seanceDetails.getDuree());
        seance.setObservations(seanceDetails.getObservations());*/

        if (seanceDetails.getPatient() != null && seanceDetails.getPatient().getCodeP() != null) {
            patientRepository.findById(seanceDetails.getPatient().getCodeP())
                    .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id: " + seanceDetails.getPatient().getCodeP()));
            seance.setPatient(seanceDetails.getPatient());
        }

        if (seanceDetails.getSoin() != null && seanceDetails.getSoin().getCodeSoin() != null) {
            soinRepository.findById(seanceDetails.getSoin().getCodeSoin())
                    .orElseThrow(() -> new RuntimeException("Soin non trouvé avec l'id: " + seanceDetails.getSoin().getCodeSoin()));
            seance.setSoin(seanceDetails.getSoin());
        }

        // Mise à jour de l'ID composite si la date, le patient ou le soin ont changé
        seance.setId(new SeanceId(
                seance.getPatient().getCodeP(),
                seance.getSoin().getCodeSoin(),
                seance.getDateSoin()
        ));

        return seanceRepository.save(seance);
    }

    @Transactional
    public void deleteSeance(SeanceId id) {
        Seance seance = seanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Séance non trouvée avec l'id: " + id));
        seanceRepository.delete(seance);
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getSeanceCountByPatient() {
        List<Object[]> results = seanceRepository.countSeancesByPatient();
        Map<String, Long> seancesByPatient = new HashMap<>();
        for (Object[] result : results) {
            seancesByPatient.put((String) result[0], (Long) result[1]);
        }
        return seancesByPatient;
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getSeanceCountBySoin() {
        List<Object[]> results = seanceRepository.countSeancesBySoin();
        Map<String, Long> seancesBySoin = new HashMap<>();
        for (Object[] result : results) {
            seancesBySoin.put((String) result[0], (Long) result[1]);
        }
        return seancesBySoin;
    }
}
