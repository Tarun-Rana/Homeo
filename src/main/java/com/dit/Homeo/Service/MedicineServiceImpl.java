package com.dit.Homeo.Service;

import com.dit.Homeo.Model.Medicine;
import com.dit.Homeo.Repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MedicineServiceImpl implements MedicineService{
    @Autowired
    MedicineRepository medicineRepository;
    @Override
    public Medicine saveMedicine(Medicine medicine) {
        medicine.setDate(LocalDateTime.now());
        medicineRepository.save(medicine);
        return medicine;
    }
}
