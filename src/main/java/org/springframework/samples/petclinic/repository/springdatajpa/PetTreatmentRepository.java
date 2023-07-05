package org.springframework.samples.petclinic.repository.springdatajpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.petclinic.model.PetTreatment;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PetTreatmentRepository extends JpaRepository<PetTreatment,Integer> {
    Iterable<PetTreatment> findByDate(String date);
//Write a native query (SELECT vets.id,vets.first_name,vets.last_name FROM pet_treatment Left JOIN vets ON vets.id=?1 Limit 1) to return string type
    @Query(value = "SELECT vets.id,vets.first_name,vets.last_name FROM pet_treatment Left JOIN vets ON vets.id=?1 Limit 1",nativeQuery = true)
    String getVetInfo(Integer id);
}
