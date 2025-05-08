package com.etudaintsystem.hospitalpatient.medical.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeanceDto {

    private Long patientId;
    private String patientNom;

    private Long soinId;
    private String soinDesignation;

    private LocalDate dateSoin;

    private String heure;

    private int duree;  // durée en minutes

    private String observations;
}
