package org.springframework.samples.petclinic.model;


import jakarta.persistence.*;


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
    private Integer id;
    private String description;
    @OneToOne
//    @JoinColumn(name = "pet_id")
    private Pet pet;
    @OneToOne
//    @JoinColumn(name = "vet_id")
    private Vet vet;
    private LocalDate date;

    public PetTreatment() {
    }

    public PetTreatment(Integer id, String description, Pet pet, Vet vet, LocalDate treatmentDate) {
        this.id = id;
        this.description = description;
        this.pet = pet;
        this.vet = vet;
        this.date = treatmentDate;
    }

    public PetTreatment(String description, Pet pet, Vet vet, LocalDate treatmentDate) {
        this.description = description;
        this.pet = pet;
        this.vet = vet;
        this.date = treatmentDate;
    }

    public Integer getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public Pet getPet() {
        return this.pet;
    }

    public Vet getVet() {
        return this.vet;
    }

    public LocalDate getTreatmentDate() {
        return this.date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;

    }

    public void setPet(Pet pet) {
        this.pet = pet;

    }

    public void setVet(Vet vet) {
        this.vet = vet;

    }

    public void setTreatmentDate(LocalDate treatmentDate) {
        this.date = treatmentDate;

    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", description='" + getDescription() + "'" + ", pet='" + getPet() + "'"
                + ", vet='" + getVet() + "'" + ", treatmentDate='" + getTreatmentDate() + "'" + "}";
    }

}
