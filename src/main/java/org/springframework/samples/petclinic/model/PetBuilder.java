package org.springframework.samples.petclinic.model;


import java.time.LocalDate;
import java.util.List;

//Builder Pattern to create Pet object
public class PetBuilder {

    private Pet pet;

    public PetBuilder() {
        pet = new Pet();
    }

    public PetBuilder withBirthDate(LocalDate birthDate) {
        pet.setBirthDate(birthDate);
        return this;
    }

    public PetBuilder withType(PetType type) {
        pet.setType(type);
        return this;
    }

    public PetBuilder withOwner(Owner owner) {
        pet.setOwner(owner);
        return this;
    }

    public PetBuilder withVisits(List<Visit> visits) {
        pet.setVisits(visits);
        return this;
    }

    public Pet build() {
        return pet;
    }
}
