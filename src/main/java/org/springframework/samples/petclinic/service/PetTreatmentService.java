package org.springframework.samples.petclinic.service;

import org.springframework.samples.petclinic.model.PetTreatment;
import org.springframework.samples.petclinic.model.Specialty;

//    Create a PetTreatmentService interface with meths for save findById ,findAll ,update and Delete

public interface PetTreatmentService {

    void save(PetTreatment petTreatment);
    PetTreatment findById(Integer id);
    Iterable<PetTreatment> findAll();
    void update(PetTreatment petTreatment);
    void delete(Integer id);
    Iterable<PetTreatment> findByDate(String date);

}
