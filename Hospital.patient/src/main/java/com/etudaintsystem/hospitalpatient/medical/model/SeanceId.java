package com.etudaintsystem.hospitalpatient.medical.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeanceId implements Serializable {

    private Long codeP;
    private Long codeSoin;

}