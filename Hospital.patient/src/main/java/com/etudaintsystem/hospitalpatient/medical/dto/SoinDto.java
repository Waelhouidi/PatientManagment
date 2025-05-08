package com.etudaintsystem.hospitalpatient.medical.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoinDto {

    private Long codeSoin;
    private String designation;
    private String description;
    private double prix;
}
