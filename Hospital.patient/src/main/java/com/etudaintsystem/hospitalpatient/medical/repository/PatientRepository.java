package com.etudaintsystem.hospitalpatient.medical.repository;


import com.etudaintsystem.hospitalpatient.medical.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNomPContainingIgnoreCaseOrPrenomPContainingIgnoreCase(String nom, String prenom);
    boolean existsByNumTel(String numTel);
}