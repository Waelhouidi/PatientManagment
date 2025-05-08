package com.etudaintsystem.hospitalpatient.medical.mapper;

import com.etudaintsystem.hospitalpatient.medical.dto.SeanceDTO;
import com.etudaintsystem.hospitalpatient.medical.model.Seance;
import org.springframework.stereotype.Component;

@Component
public class SeanceMapper {

    public SeanceDTO toDto(Seance seance) {
        if (seance == null) {
            return null;
        }

        SeanceDTO dto = new SeanceDTO();
        dto.setCodeP(seance.getPatient().getCodeP());
        dto.setCodeSoin(seance.getSoin().getCodeSoin());
        dto.setDateSoin(seance.getDateSoin());

        // Ajouter les informations supplémentaires pour l'affichage
        dto.setNomPatient(seance.getPatient().getNomP());
        dto.setPrenomPatient(seance.getPatient().getPrenomP());
        dto.setDesignationSoin(seance.getSoin().getDesignation());

        return dto;
    }
}