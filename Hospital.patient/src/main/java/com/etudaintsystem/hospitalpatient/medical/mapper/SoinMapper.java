package com.etudaintsystem.hospitalpatient.medical.mapper;

import com.etudaintsystem.hospitalpatient.medical.dto.SoinDTO;
import com.etudaintsystem.hospitalpatient.medical.model.Soin;
import org.springframework.stereotype.Component;

@Component
public class SoinMapper {

    public SoinDTO toDto(Soin soin) {
        if (soin == null) {
            return null;
        }

        SoinDTO dto = new SoinDTO();
        dto.setCodeSoin(soin.getCodeSoin());
        dto.setDesignationSoin(soin.getDesignation());

        return dto;
    }

    public Soin toEntity(SoinDTO dto) {
        if (dto == null) {
            return null;
        }

        Soin soin = new Soin();
        soin.setCodeSoin(dto.getCodeSoin());
        soin.setDesignation(dto.getDesignationSoin());

        return soin;
    }
}
