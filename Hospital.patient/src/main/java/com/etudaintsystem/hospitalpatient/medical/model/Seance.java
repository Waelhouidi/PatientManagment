package com.etudaintsystem.hospitalpatient.medical.model;

import jakarta.persistence.*;
import lombok.*;

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

    /**
     * Custom constructor with validation for convenience.
     */
    public Seance(Patient patient, Soin soin, LocalDate dateSoin) {
        if (dateSoin == null) {
            throw new IllegalArgumentException("La date du soin est obligatoire.");
        }
        if (dateSoin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La date du soin ne peut pas être dans le futur.");
        }

        this.patient = patient;
        this.soin = soin;
        this.id = new SeanceId(
                patient != null ? patient.getCodeP() : null,
                soin != null ? soin.getCodeSoin() : null,
                dateSoin
        );
    }

    /**
     * Returns the date of the seance from the embedded ID.
     */
    @Transient
    public LocalDate getDateSoin() {
        return id != null ? id.getDateSoin() : null;
    }

    /**
     * Sets the date of the seance in the embedded ID.
     */
    @Transient
    public void setDateSoin(LocalDate dateSoin) {
        if (this.id == null) {
            this.id = new SeanceId();
        }
        this.id.setDateSoin(dateSoin);
    }

    /**
     * Keeps the embedded ID in sync before persisting or updating.
     */
    @PrePersist
    @PreUpdate
    public void updateId() {
        if (id == null) {
            id = new SeanceId();
        }
        if (patient != null) {
            id.setCodeP(patient.getCodeP());
        }
        if (soin != null) {
            id.setCodeSoin(soin.getCodeSoin());
        }
    }
}
