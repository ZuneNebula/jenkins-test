package org.springframework.samples.petclinic.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.PetTreatment;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.springdatajpa.PetTreatmentRepository;
import org.springframework.stereotype.Service;


//    Add a PetTreatmentRepository,ClinicService attribute to the class
//    Add a constructor to the class that injects the PetTreatmentRepository,ClinicService attribute
//    Implement the methods of the PetTreatmentService interface using the PetTreatmentRepository attribute
//    Annotate the class with @Service

//import required classes
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;


@Service
public class PetTreatmentServiceImpl   implements PetTreatmentService{

 private PetTreatmentRepository petTreatmentRepository;

 private ClinicService clinicService;

//Create a Logger attribute to the class
private static final Logger logger = LoggerFactory.getLogger(PetTreatmentServiceImpl.class);

    @Autowired
    public PetTreatmentServiceImpl(PetTreatmentRepository petTreatmentRepository,ClinicService clinicService) {
        this.petTreatmentRepository = petTreatmentRepository;
        this.clinicService = clinicService;

    }

     @Override
     public void save(PetTreatment petTreatment) {
//        Add info message
        logger.info("PetTreatmentServiceImpl: save");
        petTreatment.setTreatmentDate(LocalDate.now());
//        Use findAllVets method of ClinicService to get all vets
        Iterable<Vet> vets = clinicService.getAllVets();
//        Iterate over vets and set the vet of the petTreatment to the first vet if isAvailable field is True
//         Use saveVet method of ClinicService to update the vet status to False
        for (Vet vet : vets) {
            if (vet.isIs_available()) {
                petTreatment.setVet(vet);
                vet.setIs_available(false);
                clinicService.saveVet(vet);
                break;
            }
        }
         petTreatmentRepository.save(petTreatment);
     }

     @Override
     public PetTreatment findById(Integer id) {
         System.out.println(petTreatmentRepository.findById(id).get());
         return petTreatmentRepository.findById(id).get();
     }

     @Override
     public Iterable<PetTreatment> findAll() {
         return petTreatmentRepository.findAll();
     }

     @Override
     public void update(PetTreatment petTreatment) {
         petTreatmentRepository.save(petTreatment);
     }

     @Override
     public void delete(Integer id) {
         petTreatmentRepository.deleteById(id);
     }

     @Override
        public Iterable<PetTreatment> findByDate(String date) {
            return petTreatmentRepository.findByDate(date);
        }

}
