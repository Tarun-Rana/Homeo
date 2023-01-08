package com.dit.Homeo.Controller;

import com.dit.Homeo.Model.Disease;
import com.dit.Homeo.Model.Medicine;
import com.dit.Homeo.Model.Patient;
import org.apache.catalina.LifecycleState;
import org.springframework.ui.Model;
import com.dit.Homeo.Service.DiseaseService;
import com.dit.Homeo.Service.MedicineService;
import com.dit.Homeo.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private MedicineService medicineService;
    @Autowired
    private DiseaseService diseaseService;

    public PatientController() {
    }

    @GetMapping(value="/")
    public String home() {
        return "index";
    }

    @GetMapping(value="/addPatient")
    public String addPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "/addPatient";
    }

    @GetMapping(value="/about")
    public String about() {
        return "/about";
    }

    @GetMapping(value="/search")
    public String search() {
        return "/search";
    }

    @PostMapping("/addp")
    public boolean addP(Patient patient){
        return patientService.save(patient);
    }

    @PostMapping(value="/addPatient")
    public String addPatient(Patient patient, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/add-user";
        }

        if(patientService.save(patient))
            System.out.println("saved patient");
        model.addAttribute("patient",patient);
        model.addAttribute("prescription",new Medicine());
        return "/prescription";
    }
    @RequestMapping("/getPatientById/{id}")
    public String getPatientById(Model model, @PathVariable("id") String id){
        Patient patient = patientService.getPatientById(Long.parseLong(id));

        return "patient";
    }

    @PostMapping("/savePrescription/{id}")
    public String prescription(@PathVariable("id") String id, Medicine prescription){
        Patient patient = patientService.getPatientById(Long.parseLong(id));
        System.out.println(patient);
        Medicine med=medicineService.saveMedicine(prescription);
        Disease disease = new Disease();
        disease.setDisease("fancy");
        if(disease.getMedicineList()==null){
            List<Medicine> medicineList= new ArrayList<>();
            medicineList.add(med);
            disease.getMedicineList();
        }else {
            disease.getMedicineList().add(med);
        }
        Disease dis = diseaseService.save(disease);
        if(patient.getDiseaseList()==null){
            List<Disease> diseaseList=new ArrayList<>();
            diseaseList.add(dis);
            patient.setDiseaseList(diseaseList);
        }else {
            patient.getDiseaseList().add(dis);
        }
        patientService.save(patient);
        return "/search";
    }
    @GetMapping("/searchResult")
        public String getAllPatients(Model model){
        model.addAttribute("patients", patientService.getAllPatients());
        return home();
        }


}
