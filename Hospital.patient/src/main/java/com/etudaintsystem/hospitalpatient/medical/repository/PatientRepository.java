package com.etudaintsystem.hospitalpatient.medical.repository;


import com.etudaintsystem.hospitalpatient.medical.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByNomPAndPrenomP(String nomP, String prenomP);

    List<Patient> findByNomPContainingIgnoreCase(String nomP);

    @Query("SELECT p FROM Patient p WHERE p.numTel = :numTel")
    Optional<Patient> findByNumTel(@Param("numTel") String numTel);

    // Chercher des patients qui ont eu une séance pour un soin spécifique
    @Query("SELECT DISTINCT p FROM Patient p JOIN p.seances s WHERE s.soin.codeSoin = :codeSoin")
    List<Patient> findPatientsBySoin(@Param("codeSoin") Long codeSoin);

    // Vérifier si un patient existe avec le même nom, prénom et numéro de téléphone
    boolean existsByNomPAndPrenomPAndNumTel(String nomP, String prenomP, String numTel);
}
