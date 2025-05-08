package com.etudaintsystem.hospitalpatient.medical.service;

import com.etudaintsystem.hospitalpatient.medical.model.Patient;
import com.etudaintsystem.hospitalpatient.medical.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Transactional(readOnly = true)
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Patient> getPatientsByNom(String nom) {
        return patientRepository.findByNomPContainingIgnoreCase(nom);
    }

    @Transactional(readOnly = true)
    public Optional<Patient> getPatientByNumTel(String numTel) {
        return patientRepository.findByNumTel(numTel);
    }

    @Transactional
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional
    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id: " + id));

        patient.setNomP(patientDetails.getNomP());
        patient.setPrenomP(patientDetails.getPrenomP());
        patient.setNumTel(patientDetails.getNumTel());

        return patientRepository.save(patient);
    }

    @Transactional
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id: " + id));

        patientRepository.delete(patient);
    }

    @Transactional(readOnly = true)
    public List<Patient> getPatientsBySoin(Long codeSoin) {
        return patientRepository.findPatientsBySoin(codeSoin);
    }

    @Transactional(readOnly = true)
    public boolean patientExists(String nom, String prenom, String numTel) {
        return patientRepository.existsByNomPAndPrenomPAndNumTel(nom, prenom, numTel);
    }
}
