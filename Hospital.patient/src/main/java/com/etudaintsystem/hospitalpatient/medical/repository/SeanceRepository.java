package com.etudaintsystem.hospitalpatient.medical.repository;


import com.etudaintsystem.hospitalpatient.medical.model.Seance;
import com.etudaintsystem.hospitalpatient.medical.model.SeanceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, SeanceId> {

    List<Seance> findByPatientCodeP(Long codeP);

    List<Seance> findBySoinCodeSoin(Long codeSoin);

    @Query("SELECT s FROM Seance s WHERE s.dateSoin BETWEEN :dateDebut AND :dateFin")
    List<Seance> findByDateSoinBetween(
            @Param("dateDebut") LocalDate dateDebut,
            @Param("dateFin") LocalDate dateFin);

    List<Seance> findByPatientCodePAndSoinCodeSoin(Long codeP, Long codeSoin);
}