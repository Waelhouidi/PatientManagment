package com.etudaintsystem.hospitalpatient.medical.service;

import com.etudaintsystem.hospitalpatient.medical.dto.PatientDTO;
import com.etudaintsystem.hospitalpatient.medical.model.Patient;
import com.etudaintsystem.hospitalpatient.medical.exception.ResourceNotFoundException;
import com.etudaintsystem.hospitalpatient.medical.mapper.PatientMapper;
import com.etudaintsystem.hospitalpatient.medical.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Transactional(readOnly = true)
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient non trouvé avec l'ID: " + id));
        return patientMapper.toDto(patient);
    }

    @Transactional
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = patientMapper.toEntity(patientDTO);
        patient = patientRepository.save(patient);
        return patientMapper.toDto(patient);
    }

    @Transactional
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient non trouvé avec l'ID: " + id));

        existingPatient.setNomP(patientDTO.getNomP());
        existingPatient.setPrenomP(patientDTO.getPrenomP());
        existingPatient.setNumTel(patientDTO.getNumTel());

        existingPatient = patientRepository.save(existingPatient);
        return patientMapper.toDto(existingPatient);
    }

    @Transactional
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patient non trouvé avec l'ID: " + id);
        }
        patientRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<PatientDTO> searchPatients(String query) {
        return patientRepository.findByNomPContainingIgnoreCaseOrPrenomPContainingIgnoreCase(query, query)
                .stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }
}