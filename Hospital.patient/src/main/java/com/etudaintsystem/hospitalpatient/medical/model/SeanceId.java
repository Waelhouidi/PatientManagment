package com.etudaintsystem.hospitalpatient.medical.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@Getter
@Setter
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
        if (!(o instanceof SeanceId)) return false;
        SeanceId that = (SeanceId) o;
        return Objects.equals(codeP, that.codeP) &&
                Objects.equals(codeSoin, that.codeSoin) &&
                Objects.equals(dateSoin, that.dateSoin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeP, codeSoin, dateSoin);
    }
}
