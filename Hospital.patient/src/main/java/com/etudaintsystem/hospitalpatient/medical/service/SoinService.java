package com.etudaintsystem.hospitalpatient.medical.service;

import com.etudaintsystem.hospitalpatient.medical.dto.SoinDTO;
import com.etudaintsystem.hospitalpatient.medical.model.Soin;
import com.etudaintsystem.hospitalpatient.medical.exception.ResourceNotFoundException;
import com.etudaintsystem.hospitalpatient.medical.mapper.SoinMapper;
import com.etudaintsystem.hospitalpatient.medical.repository.SoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SoinService {

    private final SoinRepository soinRepository;
    private final SoinMapper soinMapper;

    @Transactional(readOnly = true)
    public List<SoinDTO> getAllSoins() {
        return soinRepository.findAll().stream()
                .map(soinMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SoinDTO getSoinById(Long id) {
        Soin soin = soinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Soin non trouvé avec l'ID: " + id));
        return soinMapper.toDto(soin);
    }

    @Transactional
    public SoinDTO createSoin(SoinDTO soinDTO) {
        Soin soin = soinMapper.toEntity(soinDTO);
        soin = soinRepository.save(soin);
        return soinMapper.toDto(soin);
    }

    @Transactional
    public SoinDTO updateSoin(Long id, SoinDTO soinDTO) {
        Soin existingSoin = soinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Soin non trouvé avec l'ID: " + id));

        existingSoin.setDesignation(soinDTO.getDesignationSoin());

        existingSoin = soinRepository.save(existingSoin);
        return soinMapper.toDto(existingSoin);
    }

    @Transactional
    public void deleteSoin(Long id) {
        if (!soinRepository.existsById(id)) {
            throw new ResourceNotFoundException("Soin non trouvé avec l'ID: " + id);
        }
        soinRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<SoinDTO> searchSoins(String query) {
        return soinRepository.findByDesignationContainingIgnoreCase(query)
                .stream()
                .map(soinMapper::toDto)
                .collect(Collectors.toList());
    }
}