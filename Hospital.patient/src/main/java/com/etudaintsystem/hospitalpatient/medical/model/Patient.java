package com.etudaintsystem.hospitalpatient.medical.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_p")
    private Long codeP;

    @NotBlank(message = "Le nom du patient est obligatoire")
    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
    @Column(name = "nom_p", nullable = false)
    private String nomP;

    @NotBlank(message = "Le prénom du patient est obligatoire")
    @Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères")
    @Column(name = "prenom_p", nullable = false)
    private String prenomP;

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$",
            message = "Format de numéro de téléphone invalide")
    @Column(name = "num_tel", nullable = false)
    private String numTel;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seance> seances = new ArrayList<>();

    // Méthode utilitaire pour ajouter une séance
    public void addSeance(Seance seance) {
        seances.add(seance);
        seance.setPatient(this);
    }

    // Méthode utilitaire pour supprimer une séance
    public void removeSeance(Seance seance) {
        seances.remove(seance);
        seance.setPatient(null);
    }
}