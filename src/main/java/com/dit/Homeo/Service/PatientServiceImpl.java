package com.dit.Homeo.Service;

import com.dit.Homeo.Model.Patient;
import com.dit.Homeo.Model.SearchInputs;
import com.dit.Homeo.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;
    @Override
    public boolean save(Patient patient) {
        patient.setDate(LocalDateTime.now());
         if(patientRepository.save(patient).getId().equals(null))
             return false;
         return true;
    }

    @Override
    public Patient getPatientById(long id) {
        Patient patient=patientRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return patient;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> getPatientsByValue(SearchInputs search) {
        if(search.getField().equals("Name")){
            return patientRepository.findAllByNameIs(search.getSearch());
        }else if( search.getField().equals("Address")){
            return  patientRepository.findAllByAddressIs(search.getSearch());
        }else{
            return patientRepository.findAllByContactInformationIs(search.getSearch());
        }

    }
}
