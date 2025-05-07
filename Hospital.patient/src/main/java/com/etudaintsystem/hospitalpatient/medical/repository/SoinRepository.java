package com.etudaintsystem.hospitalpatient.medical.repository;


import com.etudaintsystem.hospitalpatient.medical.model.Soin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SoinRepository extends JpaRepository<Soin, Long> {

    Optional<Soin> findByDesignation(String designation);

    List<Soin> findByDesignationContainingIgnoreCase(String designation);

    boolean existsByDesignation(String designation);

    // Trouver tous les soins qu'un patient a reçus
    @Query("SELECT DISTINCT s FROM Soin s JOIN s.seances se WHERE se.patient.codeP = :codeP")
    List<Soin> findSoinsByPatientId(@Param("codeP") Long codeP);

    // Trouver les soins les plus fréquemment administrés
    @Query("SELECT s, COUNT(s) AS total FROM Soin s JOIN s.seances se GROUP BY s ORDER BY total DESC")
    List<Object[]> findMostFrequentSoins();
}
