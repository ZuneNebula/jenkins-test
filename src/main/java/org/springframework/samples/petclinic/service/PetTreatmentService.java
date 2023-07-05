package org.springframework.samples.petclinic.service;

import org.springframework.samples.petclinic.model.PetTreatment;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;

import java.util.List;

//    Create a PetTreatmentService interface with meths for save findById ,findAll ,update and Delete

public interface PetTreatmentService {

    PetTreatment save(PetTreatment petTreatment) throws Exception;
    PetTreatment findById(Integer id);
    Iterable<PetTreatment> findAll();
    void update(PetTreatment petTreatment);
    void delete(Integer id);
    Iterable<PetTreatment> findByDate(String date);
    Vet getVetInfo(Integer id);
}
