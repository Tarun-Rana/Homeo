package com.dit.Homeo.Repository;

import com.dit.Homeo.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;


public interface PatientRepository extends JpaRepository<Patient,Long> {
    List<Patient> findAllByNameIs(String search);

    List<Patient> findAllByAddressIs(String search);

    List<Patient> findAllByContactInformationIs (String search);
}
