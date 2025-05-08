package com.etudaintsystem.hospitalpatient.medical.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeanceDTO {

    @NotNull(message = "L'identifiant du patient est obligatoire")
    private Long codeP;

    @NotNull(message = "L'identifiant du soin est obligatoire")
    private Long codeSoin;

    @NotNull(message = "La date du soin est obligatoire")
    @PastOrPresent(message = "La date du soin ne peut pas être dans le futur")
    private LocalDate dateSoin;

    // Champs additionnels pour l'affichage
    private String nomPatient;
    private String prenomPatient;
    private String designationSoin;
}