package tn.spring.pispring.Serviceworkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.MailStructure;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("$(spring.mail.username)")
    private String fromMail;
    public void  sendMail(String mail, MailStructure mailStructure){
        SimpleMailMessage message =new SimpleMailMessage();
        message.setFrom("fromMail");


        message.setSubject(mailStructure.getSubject());
        message.setText(mailStructure.getMessage()+ mailStructure.getQrCodeUrl());
        message.setTo(mail);
        mailSender.send(message);
        //mailSender.send(qrCodeUrl);
        System.out.println("mail send successfully");
    }
    public void sendQRCodeByEmail(String email, byte[] qrCodeImage) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true); // Activer le mode multipart
            helper.setTo(email);
            helper.setSubject("Your Event participation QR Code");

            // Créer le contenu HTML du message avec une image en ligne
            String htmlContent = "<p>Dear User,</p>" +
                    "<p>Please find attached your event participation QR code.</p>" +
                    "<img src='cid:qrCodeImage'>"+
                    "<p>Regards,<br>Your Application Team</p>";

            helper.setText(htmlContent, true); // Indiquer que le contenu est au format HTML

            // Redimensionner l'image du QR code
            BufferedImage resizedImage = resizeImage(qrCodeImage, 200, 200); // Réduire la résolution à 200x200 pixels

            // Attachement du QR code
            ByteArrayResource qrCodeResource = new ByteArrayResource(imageToByteArray(resizedImage));
            helper.addAttachment("qr_code.png", qrCodeResource);



            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée (par exemple, en enregistrant dans un fichier journal)
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private BufferedImage resizeImage(byte[] imageData, int width, int height) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        BufferedImage originalImage = ImageIO.read(bais);

        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }

    private byte[] imageToByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }




}
