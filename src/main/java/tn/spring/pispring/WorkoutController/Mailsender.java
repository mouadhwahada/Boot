package tn.spring.pispring.WorkoutController;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.MailStructure;
import tn.spring.pispring.Serviceworkout.EmailSenderService;
@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/mail")
public class Mailsender {
    @Autowired
    EmailSenderService emailSenderService;
    @PostMapping("/send/{mail}")

    public String sendMail(@PathVariable String mail, @RequestBody MailStructure mailStructure){
       emailSenderService.sendMail(mail,mailStructure);
       return  "seccessfluy";
}
}
