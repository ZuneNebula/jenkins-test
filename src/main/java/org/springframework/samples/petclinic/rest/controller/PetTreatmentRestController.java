package org.springframework.samples.petclinic.rest.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.PetTreatment;
import org.springframework.samples.petclinic.service.PetTreatmentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

//   Add a PetTreatmentService attribute to the class
//    Add a constructor to the class that injects the PetTreatmentService attribute
//    Use the PetTreatmentService attribute to implement the methods of the PetTreatmentRestController class
//    Annotate the class with @RestController
//    Annotate the methods with @GetMapping, @PostMapping, @PutMapping and @DeleteMapping
//    Annotate the methods with @ResponseStatus
@RestController
public class PetTreatmentRestController {

    private PetTreatmentService petTreatmentService;

    @Autowired
    public PetTreatmentRestController(PetTreatmentService petTreatmentService){
        this.petTreatmentService = petTreatmentService;
    }
//Add try catch block to the implemented methods
    @GetMapping("/api/pettreatments")
    public ResponseEntity<Collection<PetTreatment>> getAllPetTreatments(){
        try {
            Collection<PetTreatment> petTreatments = (Collection<PetTreatment>) petTreatmentService.findAll();
            if (petTreatments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(petTreatments, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/pettreatments/{petTreatmentId}")
    public ResponseEntity<PetTreatment> getPetTreatment(@PathVariable("petTreatmentId") int petTreatmentId){
        try {
            PetTreatment petTreatment = petTreatmentService.findById(petTreatmentId);
            if (petTreatment == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(petTreatment, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/pettreatments")
    public ResponseEntity<Void> addPetTreatment(@RequestBody PetTreatment petTreatment){
        try {
            petTreatmentService.save(petTreatment);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/pettreatments/{petTreatmentId}")
    public ResponseEntity<PetTreatment> updatePetTreatment(@PathVariable("petTreatmentId") int petTreatmentId, @RequestBody PetTreatment petTreatment){
       try {
              PetTreatment currentPetTreatment = petTreatmentService.findById(petTreatmentId);
              if (currentPetTreatment == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
              }

              petTreatmentService.save(currentPetTreatment);
              return new ResponseEntity<>(currentPetTreatment, HttpStatus.OK);
       }
         catch (Exception e){
              return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    @DeleteMapping("/api/pettreatments/{petTreatmentId}")
    public ResponseEntity<Void> deletePetTreatment(@PathVariable("petTreatmentId") int petTreatmentId){
        try {
            PetTreatment petTreatment = petTreatmentService.findById(petTreatmentId);
            if (petTreatment == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            petTreatmentService.delete(petTreatmentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    GetMapping to get all the pet treatments for a date

    @GetMapping("/api/pettreatments/date/{date}")
    public ResponseEntity<Collection<PetTreatment>> getAllPetTreatmentsByDate(@PathVariable("date") String date){
        try {
            Collection<PetTreatment> petTreatments = (Collection<PetTreatment>) petTreatmentService.findByDate(date);
            if (petTreatments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(petTreatments, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
