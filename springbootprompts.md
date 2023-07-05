
//Factory Pattern to create Pet object

//Builder Pattern to create Pet object



//Create a PetTreatment POJO class with the following fields:
//id (Integer)
//description (String)
//pet (Pet)
//vet (Vet)
//date (LocalDate)

//Write a native query (SELECT vets.id,vets.first_name,vets.last_name FROM pet_treatment Left JOIN vets ON vets.id=?1 Limit 1) to return string type
 
//Create a PetTreatmentService interface with meths for save findById ,findAll ,update and Delete


//Add a PetTreatmentRepository,ClinicService attribute to the class
//Add a constructor to the class that injects the PetTreatmentRepository,ClinicService attribute
//Implement the methods of the PetTreatmentService interface using the PetTreatmentRepository attribute
//Annotate the class with @Service

//Use findAllVets method of ClinicService to get all vets
//Iterate over vets and set the vet of the petTreatment to the first vet that has the same specialty as the petTreatment
//Use findAll method to get all pettreatments and check whether current vetid is available for current date

//   Add a PetTreatmentService attribute to the class
//    Add a constructor to the class that injects the PetTreatmentService attribute
//    Use the PetTreatmentService attribute to implement the methods of the PetTreatmentRestController class
//    Annotate the class with @RestController
//    Annotate the methods with @GetMapping, @PostMapping, @PutMapping and @DeleteMapping
//    Annotate the methods with @ResponseStatus


//Add try catch block to the implemented methods

//Create field as ownerEmailAddress of type String and implement regex patterns to validate the email address.



