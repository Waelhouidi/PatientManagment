package com.etudaintsystem.hospitalpatient.medical.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeanceId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "code_p")
    private Long codeP;

    @Column(name = "code_soin")
    private Long codeSoin;

    @Column(name = "date_soin")
    private LocalDate dateSoin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeanceId seanceId = (SeanceId) o;
        return Objects.equals(codeP, seanceId.codeP) &&
                Objects.equals(codeSoin, seanceId.codeSoin) &&
                Objects.equals(dateSoin, seanceId.dateSoin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeP, codeSoin, dateSoin);
    }
}
