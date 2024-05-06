package tn.spring.pispring.Serviceworkout;


import com.google.zxing.WriterException;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Event;
import tn.spring.pispring.Entities.Participation;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Interfaceworkout.ParticipationInterface;

import tn.spring.pispring.dto.ParticipationDTO;
import tn.spring.pispring.repo.UserRepository;
import tn.spring.pispring.repositoriesworkout.EventRepository;
import tn.spring.pispring.repositoriesworkout.ParticipationRepository;

import javax.persistence.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParticipationService implements ParticipationInterface {
    @Autowired
    private ParticipationRepository participationRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ExerciceService eventService;
    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public Participation creatParticipation(Participation participation, Long idUser, Long idEvent) {
        User user = userRepo.findById(idUser).orElse(null);
        Event event = eventRepository.findById(idEvent).orElse(null);
        participation.setUser(user);
        participation.setEvent(event);
        try {
            String part="ID:"+participation.getId()+" User:"+participation.getUser().getEmail()+" Event:"+participation.getEvent().getTitre();
            byte[] qrcode=QRCodeGenerator.generateQRCodeImage(part);
            emailSenderService.sendQRCodeByEmail(user.getEmail(),qrcode);

        } catch (WriterException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return participationRepository.save(participation);
    }
    private String saveQRCodeImage(byte[] qrCodeImage) throws IOException {
        Path qrCodeDirectoryPath = Paths.get("src/main/resources/uploads/");
        Files.createDirectories(qrCodeDirectoryPath);

        String qrCodeFileName = "qr_code_" + System.currentTimeMillis() + ".png";
        Path qrCodeFilePath = ((Path) qrCodeDirectoryPath).resolve(qrCodeFileName);

        Files.write(qrCodeFilePath, qrCodeImage);

        return qrCodeFilePath.toString();
    }
    /*

    @Override
    public Participation updatParticipation(Participation participation) {
        return participationRepository.save(participation);
    }

*/
    public Participation updatParticipation(Participation updatedParticipation) {
        return participationRepository.findById(updatedParticipation.getId())
                .map(existingParticipation -> {
                    existingParticipation.setRegistrationDate(updatedParticipation.getRegistrationDate());
                    existingParticipation.setInjury(updatedParticipation.getInjury());
                    existingParticipation.setEmergencyNumber(updatedParticipation.getEmergencyNumber());
                    // Vous pouvez également mettre à jour d'autres champs si nécessaire
                    return participationRepository.save(existingParticipation);
                })
                .orElseThrow(() -> new IllegalArgumentException("Participation not found"));
    }


    @Override
    public void deleteParticipation(Long id_participation) {
        Participation participation = participationRepository.findById(id_participation)
                .orElseThrow(() -> new EntityNotFoundException("Participation not found with id: " + id_participation));

        // Récupérer l'événement associé à la participation
        Event event = participation.getEvent();

        // Augmenter maxParticipation de l'événement associé
        event.setMaxParticiaption(event.getMaxParticiaption() + 1);

        // Mettre à jour l'événement dans la base de données
        // eventRepository.updatEvent(event);

        // Supprimer la participation
        participationRepository.deleteById(id_participation);
    }

    @Override
    public List<Participation> getAllParticipation() {
        return participationRepository.findAll();
    }

    @Override
    public Participation getParticipationById(Long id_participation) {
        return participationRepository.findById(id_participation).orElse(null);
    }

    @Override
    public List<Participation> getParticipationsByEvent(Long idEvent) {
        return getAllParticipation()
                .stream()
                .filter(p -> p.getEvent().getId().equals(idEvent))
                .collect(Collectors.toList());
    }

    //@Override
    /*public List<Participation> getParticipationsByUser(Long idUser) {
        return getAllParticipation()
                .stream()
                .filter(p -> p.getUser().getId().equals(idUser))

                .peek(p -> System.out.println("Event Title: " + p.getEvent().getTitre()))
                .collect(Collectors.toList());
    }
    */
/*
@Override
public List<Map<String, Object>> getParticipationsByUser(Long idUser) {
    return getAllParticipation()
            .stream()
            .filter(p -> p.getUser().getId().equals(idUser))
            .map(p -> {
                Map<String, Object> participationMap = new HashMap<>();
                participationMap.put("id", p.getId());
                participationMap.put("registrationDate", p.getRegistrationDate());
                participationMap.put("injury", p.getInjury());
                participationMap.put("emergencyNumber", p.getEmergencyNumber());
                participationMap.put("eventTitle", p.getEvent().getTitre());
                return participationMap;
            })
            .collect(Collectors.toList());

}
*/
    @Override
    public List<ParticipationDTO> getParticipationsByUser(Long idUser) {
        List<ParticipationDTO> result=new ArrayList<>();
        List<Participation> input=getAllParticipation()
                .stream()
                .filter(p -> p.getUser().getId().equals(idUser))
                .collect(Collectors.toList());
        for(Participation participation:input){
            ParticipationDTO participationDTO=new ParticipationDTO();
            participationDTO.setIdUser(participation.getUser().getId());
            participationDTO.setInjury(participation.getInjury());
            participationDTO.setId(participation.getId());
            participationDTO.setIdEvent(participation.getEvent().getId());
            participationDTO.setEmergencyNumber(participation.getEmergencyNumber());
            participationDTO.setRegistrationDate(participation.getRegistrationDate());
            result.add(participationDTO);
        }
        return result;
    }



    @Override
    public List<Event> getEventsByUser(Long idUser) {
        return participationRepository.findEventsByUserId(idUser);
    }
    }

