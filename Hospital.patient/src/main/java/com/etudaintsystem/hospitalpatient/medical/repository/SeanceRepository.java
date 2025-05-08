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

    List<Seance> findByDateSoin(LocalDate dateSoin);

    @Query("SELECT s FROM Seance s WHERE s.patient.codeP = :codeP AND s.id.dateSoin BETWEEN :dateDebut AND :dateFin")
    List<Seance> findByPatientAndDateBetween(
            @Param("codeP") Long codeP,
            @Param("dateDebut") LocalDate dateDebut,
            @Param("dateFin") LocalDate dateFin
    );

    List<Seance> findByPatientCodePAndSoinCodeSoin(Long codeP, Long codeSoin);

    List<Seance> findByDateSoinBetween(LocalDate dateDebut, LocalDate dateFin);

    boolean existsByPatientCodePAndSoinCodeSoinAndDateSoin(Long codeP, Long codeSoin, LocalDate dateSoin);

    boolean existsByPatientCodePAndSoinCodeSoinAndDateSoinAndHeure(Long codeP, Long codeSoin, LocalDate dateSoin, String heure);

    @Query("SELECT s.patient.nomP, COUNT(s) FROM Seance s GROUP BY s.patient.nomP")
    List<Object[]> countSeancesByPatient();

    @Query("SELECT s.soin.designation, COUNT(s) FROM Seance s GROUP BY s.soin.designation")
    List<Object[]> countSeancesBySoin();
}
