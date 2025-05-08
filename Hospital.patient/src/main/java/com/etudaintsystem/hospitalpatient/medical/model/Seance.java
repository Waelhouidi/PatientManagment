package com.etudaintsystem.hospitalpatient.medical.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "seances")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seance {

    @EmbeddedId
    private SeanceId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("codeP")
    @JoinColumn(name = "code_p")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("codeSoin")
    @JoinColumn(name = "code_soin")
    private Soin soin;

    @NotNull(message = "La date du soin est obligatoire")
    @PastOrPresent(message = "La date du soin ne peut pas être dans le futur")
    @Column(name = "date_soin", nullable = false)
    private LocalDate dateSoin;
}