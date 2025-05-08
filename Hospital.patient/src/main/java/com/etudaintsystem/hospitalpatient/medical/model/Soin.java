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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeSoin;

    @NotBlank(message = "La désignation du soin est obligatoire")
    @Size(min = 2, max = 100, message = "La désignation doit contenir entre 2 et 100 caractères")
    @Column(nullable = false)
    private String designation;

    @OneToMany(mappedBy = "soin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seance> seances = new ArrayList<>();
}