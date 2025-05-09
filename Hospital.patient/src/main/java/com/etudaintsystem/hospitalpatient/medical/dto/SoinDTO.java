package com.etudaintsystem.hospitalpatient.medical.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoinDTO {

    private Long codeSoin;

    @NotBlank(message = "La désignation du soin est obligatoire")
    @Size(min = 2, max = 100, message = "La désignation doit contenir entre 2 et 100 caractères")
    private String designationSoin;
}
