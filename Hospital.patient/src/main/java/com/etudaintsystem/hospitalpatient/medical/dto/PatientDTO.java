package com.etudaintsystem.hospitalpatient.medical.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Long codeP;

    @NotBlank(message = "Le nom du patient est obligatoire")
    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
    private String nomP;

    @NotBlank(message = "Le prénom du patient est obligatoire")
    @Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères")
    private String prenomP;

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    @Pattern(regexp = "^(\\+[0-9]{1,3})?[0-9]{9,12}$", message = "Format de numéro de téléphone invalide")
    private String numTel;
}
