//package org.springframework.samples.petclinic.rest.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.samples.petclinic.util.Mailer;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/mail")
//public class MailController {
//
//    /*create a simple endpoint which accepts the target email and sends them hello world*/
//
//    @Autowired
//    Mailer mailer;
//
//    @PostMapping("/send")
//    public String sendMail(@RequestParam("email") String email) {
//        mailer.send(email,"Hello World","Hello World");
//        return "Email Sent";
//    }
//}
