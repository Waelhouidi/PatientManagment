package com.etudaintsystem.hospitalpatient.medical.mapper;


import com.etudaintsystem.hospitalpatient.medical.dto.PatientDTO;
import com.etudaintsystem.hospitalpatient.medical.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public PatientDTO toDto(Patient patient) {
        if (patient == null) {
            return null;
        }

        PatientDTO dto = new PatientDTO();
        dto.setCodeP(patient.getCodeP());
        dto.setNomP(patient.getNomP());
        dto.setPrenomP(patient.getPrenomP());
        dto.setNumTel(patient.getNumTel());

        return dto;
    }

    public Patient toEntity(PatientDTO dto) {
        if (dto == null) {
            return null;
        }

        Patient patient = new Patient();
        patient.setCodeP(dto.getCodeP());
        patient.setNomP(dto.getNomP());
        patient.setPrenomP(dto.getPrenomP());
        patient.setNumTel(dto.getNumTel());

        return patient;
    }
}