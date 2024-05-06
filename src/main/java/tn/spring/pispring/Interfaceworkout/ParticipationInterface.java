package tn.spring.pispring.Interfaceworkout;

import tn.spring.pispring.Entities.Event;
import tn.spring.pispring.Entities.Participation;
import tn.spring.pispring.dto.ParticipationDTO;

import java.util.List;
import java.util.Map;

public interface ParticipationInterface {
    Participation creatParticipation(Participation participation,Long idUser,Long idEvent);
    Participation updatParticipation(Participation participation);
    void deleteParticipation(Long id_participation);
    List<Participation> getAllParticipation();
    Participation getParticipationById(Long id_participation);
    List<Participation> getParticipationsByEvent(Long idEvent);

    List<ParticipationDTO> getParticipationsByUser(Long idUser);
    List<Event> getEventsByUser(Long idUser);
}
