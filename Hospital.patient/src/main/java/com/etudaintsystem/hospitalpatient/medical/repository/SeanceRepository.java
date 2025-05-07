package com.etudaintsystem.hospitalpatient.medical.repository;


import com.etudaintsystem.hospitalpatient.medical.model.Seance;
import com.etudaintsystem.hospitalpatient.medical.model.Soin;
import com.etudaintsystem.hospitalpatient.medical.model.SeanceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, SeanceId> {

    // Trouve toutes les séances d'un patient spécifique
    List<Seance> findByPatientCodeP(Long codeP);

    // Trouve toutes les séances pour un soin spécifique
    List<Seance> findBySoinCodeSoin(Long codeSoin);

    // Trouve toutes les séances pour une date spécifique
    List<Seance> findByDateSoin(LocalDate dateSoin);

    // Trouve toutes les séances d'un patient pour une période donnée
    @Query("SELECT s FROM Seance s WHERE s.patient.codeP = :codeP AND s.dateSoin BETWEEN :dateDebut AND :dateFin")
    List<Seance> findByPatientAndDateBetween(
            @Param("codeP") Long codeP,
            @Param("dateDebut") LocalDate dateDebut,
            @Param("dateFin") LocalDate dateFin
    );

    // Trouve toutes les séances pour un patient et un soin spécifique
    List<Seance> findByPatientCodePAndSoinCodeSoin(Long codeP, Long codeSoin);

    // Trouve toutes les séances pour une période donnée
    List<Seance> findByDateSoinBetween(LocalDate dateDebut, LocalDate dateFin);

    // Vérifie si une séance existe pour un patient, un soin et une date
    boolean existsByPatientCodePAndSoinCodeSoinAndDateSoin(Long codeP, Long codeSoin, LocalDate dateSoin);
}
