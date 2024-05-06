package tn.spring.pispring.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.ServiceIMP.MailService;

import javax.mail.MessagingException;

@RestController

@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send-email")
    public String sendEmail() {
        String tomail = "aminemekni626@gmail.com";
        String subject = "Test d'envoi d'e-mail";
        String body = "Ceci est un test d'envoi d'e-mail depuis Spring Boot.";

        try {
            mailService.sendEmail(tomail, subject, body);
            return "E-mail envoyé avec succès !";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Erreur lors de l'envoi de l'e-mail.";
        }
    }











}

