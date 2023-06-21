package org.springframework.samples.petclinic.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.PetTreatment;
import org.springframework.samples.petclinic.repository.springdatajpa.PetTreatmentRepository;
import org.springframework.stereotype.Service;


//    Add a PetTreatmentRepository attribute to the class
//    Add a constructor to the class that injects the PetTreatmentRepository attribute
//    Implement the methods of the PetTreatmentService interface using the PetTreatmentRepository attribute
//    Annotate the class with @Service
//import required classes
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Service
public class PetTreatmentServiceImpl   implements PetTreatmentService{

 private PetTreatmentRepository petTreatmentRepository;
//Create a Logger attribute to the class
private static final Logger logger = LoggerFactory.getLogger(PetTreatmentServiceImpl.class);


    @Autowired
     public PetTreatmentServiceImpl(PetTreatmentRepository petTreatmentRepository){

            this.petTreatmentRepository = petTreatmentRepository;
     }

     @Override
     public void save(PetTreatment petTreatment) {
//        Add info message
        logger.info("PetTreatmentServiceImpl: save");
         petTreatmentRepository.save(petTreatment);
     }

     @Override
     public PetTreatment findById(Integer id) {
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
