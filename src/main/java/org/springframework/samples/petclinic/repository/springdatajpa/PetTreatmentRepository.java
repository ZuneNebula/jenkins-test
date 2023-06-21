package org.springframework.samples.petclinic.repository.springdatajpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.PetTreatment;
import org.springframework.stereotype.Repository;

@Repository
public interface PetTreatmentRepository extends JpaRepository<PetTreatment,Integer> {
    Iterable<PetTreatment> findByDate(String date);

//
}
