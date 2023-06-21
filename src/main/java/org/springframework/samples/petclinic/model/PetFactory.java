package org.springframework.samples.petclinic.model;


import java.time.LocalDate;
import java.util.List;


//Factory Pattern to create Pet object
public class PetFactory {
    public static Pet createPet(LocalDate birthDate, PetType type, Owner owner, List<Visit> visits) {
        Pet pet = new Pet();
        pet.setBirthDate(birthDate);
        pet.setType(type);
        pet.setOwner(owner);
        pet.setVisits(visits);
        return pet;
    }
}
