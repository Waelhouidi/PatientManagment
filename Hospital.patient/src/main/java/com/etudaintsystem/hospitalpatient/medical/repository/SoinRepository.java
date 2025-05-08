package com.etudaintsystem.hospitalpatient.medical.repository;



import com.etudaintsystem.hospitalpatient.medical.model.Soin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SoinRepository extends JpaRepository<Soin, Long> {
    List<Soin> findByDesignationContainingIgnoreCase(String designation);
    Optional<Soin> findByDesignationIgnoreCase(String designation);
}
