package org.springframework.samples.petclinic.model;


import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;


import java.time.LocalDate;

//Create a PetTreatment POJO class with the following fields:
//id (Integer)
//description (String)
//pet (Pet)
//vet (Vet)
//date (LocalDate)
@Entity
@Table
public class PetTreatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @OneToOne
//    @JoinColumn(name = "pet_id")
    private Pet pet;
    @OneToOne
//    @JoinColumn(name = "vet_id")
    private Vet vet;
    private LocalDate date;
    @Transient
    private String speciality;

    public PetTreatment() {
    }

    public PetTreatment(Integer id, String description, Pet pet, Vet vet, LocalDate date, String speciality) {
        this.id = id;
        this.description = description;
        this.pet = pet;
        this.vet = vet;
        this.date = date;
        this.speciality = speciality;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", description='" + getDescription() + "'" + ", pet='" + getPet() + "'"
                + ", vet='" + getVet() + "'" + ", treatmentDate='" + getDate() + "'" + "}";
    }

}
