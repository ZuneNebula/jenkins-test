package org.springframework.samples.petclinic.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.PetTreatment;
import org.springframework.samples.petclinic.model.Vet;
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
import java.util.List;


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
     public PetTreatment save(PetTreatment petTreatment) throws Exception {
//        Add info message
        logger.info("PetTreatmentServiceImpl: save");
        petTreatment.setTreatmentDate(LocalDate.now());
//        Use findAllVets method of ClinicService to get all vets
        Iterable<Vet> vets = clinicService.findAllVets();
//        Iterate over vets and set the vet of the petTreatment to the first vet that has the same specialty as the petTreatment

        List<PetTreatment> petTreatments = petTreatmentRepository.findAll();

        for (Vet vet : vets) {
            boolean isVetIdAvailableInPetTreatment = false;
            boolean isCurrentDateAvailableInPetTreatment = false;
            if ( vet.getSpecialties().toString().contains(petTreatment.getSpeciality())) {
                //Use findAll method to get all pettreatments and check whether current vetid is available for current date

                if(petTreatments.size()==0){
                    petTreatment.setVet(vet);
                    System.out.println(petTreatment);
                    petTreatmentRepository.save(petTreatment);
                    return petTreatment;
                }

                for (PetTreatment petTreatment1 : petTreatments) {
                    System.out.println(vet.getFirstName());
                    if (petTreatment1.getVet().getId() == vet.getId()) {
                        System.out.println("vet id equal");
                       isVetIdAvailableInPetTreatment = true;
                       break;
                    }
                }
                if(isVetIdAvailableInPetTreatment){
                    System.out.println("vet id available");
                    for (PetTreatment petTreatment1 : petTreatments) {
                        if (petTreatment1.getVet().getId() == vet.getId() && petTreatment1.getTreatmentDate().equals(LocalDate.now())) {
                            isCurrentDateAvailableInPetTreatment=true;
                            System.out.println("current date available");
                            break;
                        }
                    }
                    if(!isCurrentDateAvailableInPetTreatment){
                        System.out.println("vet id avail current date not available");
                        petTreatment.setVet(vet);
                        System.out.println(petTreatment);
                        petTreatmentRepository.save(petTreatment);
                        return petTreatment;
                    }
                }
                else {
                    System.out.println("vet id not available");
                    petTreatment.setVet(vet);
                    System.out.println(petTreatment);
                    petTreatmentRepository.save(petTreatment);
                    return petTreatment;
                }

            }
        }
        throw new Exception("No vet available for current date");
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
