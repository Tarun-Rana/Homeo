package com.dit.Homeo.Service;

import com.dit.Homeo.Model.Patient;
import com.dit.Homeo.Model.SearchInputs;

import java.util.List;

public interface PatientService {
    public boolean save(Patient patient);
    public Patient getPatientById(long id);

    List<Patient> getAllPatients();

    List<Patient> getPatientsByValue(SearchInputs search);
}
