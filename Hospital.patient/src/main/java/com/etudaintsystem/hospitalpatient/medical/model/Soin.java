package com.etudaintsystem.hospitalpatient.medical.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "soins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Soin {
    public Long getCodeSoin() {
        return codeSoin;
    }

    public void setCodeSoin(Long codeSoin) {
        this.codeSoin = codeSoin;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_soin")
    private Long codeSoin;

    @NotBlank(message = "La désignation du soin est obligatoire")
    @Size(min = 2, max = 100, message = "La désignation doit contenir entre 2 et 100 caractères")
    @Column(name = "designation", nullable = false)
    private String designation;

    @OneToMany(mappedBy = "soin", cascade = CascadeType.ALL)
    private List<Seance> seances = new ArrayList<>();

    // Méthode utilitaire pour ajouter une séance
    public void addSeance(Seance seance) {
        seances.add(seance);
        seance.setSoin(this);
    }

    // Méthode utilitaire pour supprimer une séance
    public void removeSeance(Seance seance) {
        seances.remove(seance);
        seance.setSoin(null);
    }
}
