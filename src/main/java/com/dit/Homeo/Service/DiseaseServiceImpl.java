package com.dit.Homeo.Service;

import com.dit.Homeo.Model.Disease;
import com.dit.Homeo.Repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DiseaseServiceImpl implements DiseaseService{
    @Autowired
    DiseaseRepository diseaseRepository;
    @Override
    public Disease save(Disease disease) {
        disease.setDate(LocalDateTime.now());
        diseaseRepository.save(disease);
        return disease;
    }
}
