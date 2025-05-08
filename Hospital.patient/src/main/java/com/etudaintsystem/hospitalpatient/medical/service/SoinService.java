package com.etudaintsystem.hospitalpatient.medical.service;


import com.etudaintsystem.hospitalpatient.medical.model.Soin;
import com.etudaintsystem.hospitalpatient.medical.repository.SoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class SoinService {

    @Autowired
    private SoinRepository soinRepository;

    @Transactional(readOnly = true)
    public List<Soin> getAllSoins() {
        return soinRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Soin> getSoinById(Long id) {
        return soinRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Soin> getSoinByDesignation(String designation) {
        return soinRepository.findByDesignation(designation);
    }

    @Transactional(readOnly = true)
    public List<Soin> getSoinsByDesignationContaining(String designation) {
        return soinRepository.findByDesignationContainingIgnoreCase(designation);
    }

    @Transactional
    public Soin saveSoin(Soin soin) {
        return soinRepository.save(soin);
    }

    @Transactional
    public Soin updateSoin(Long id, Soin soinDetails) {
        Soin soin = soinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soin non trouvé avec l'id: " + id));

        soin.setDesignation(soinDetails.getDesignation());

        return soinRepository.save(soin);
    }

    @Transactional
    public void deleteSoin(Long id) {
        Soin soin = soinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soin non trouvé avec l'id: " + id));

        soinRepository.delete(soin);
    }

    @Transactional(readOnly = true)
    public List<Soin> getSoinsByPatientId(Long codeP) {
        return soinRepository.findSoinsByPatientId(codeP);
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getMostFrequentSoins() {
        List<Object[]> results = soinRepository.findMostFrequentSoins();
        Map<String, Long> soinFrequency = new HashMap<>();

        for (Object[] result : results) {
            Soin soin = (Soin) result[0];
            Long count = (Long) result[1];
            soinFrequency.put(soin.getDesignation(), count);
        }

        return soinFrequency;
    }

    @Transactional(readOnly = true)
    public boolean soinExistsByDesignation(String designation) {
        return soinRepository.existsByDesignation(designation);
    }
}
