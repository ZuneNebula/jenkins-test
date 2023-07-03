package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.stereotype.Repository;



@Repository
public interface VetRepo extends JpaRepository<Vet,Integer> {


}
