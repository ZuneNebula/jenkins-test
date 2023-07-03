package org.springframework.samples.petclinic.util;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Mailer {

//    @Value("${aws.accessKeyId}")
//    private String accessKey;
//
//    @Value("${aws.secretKey}")
//    private String secretKey;

    /*send a mail using amazon ses, recieve string data and target email from the arguments*/
    public static void send(String to,String subject,String body) {
        //ATTACH local aws credentials

        System.setProperty("aws.accessKeyId", "AKIASOB3KWOU2MKS6Y7E");
        System.setProperty("aws.secretKey", "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");

        //AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

        //create a new amazon ses client
        AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
//        //create a new send email request
        SendEmailRequest request = new SendEmailRequest()
            .withDestination(
                new Destination().withToAddresses(to))
            .withMessage(new Message()
                .withBody(new Body()
                    .withHtml(new Content()
                        .withCharset("UTF-8").withData(body)))
                .withSubject(new Content()
                    .withCharset("UTF-8").withData(subject)))
            .withSource("mihirsharmacs@gmail.com");
        //try to send the email
        try {
            client.sendEmail(request);
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }

    }

}
