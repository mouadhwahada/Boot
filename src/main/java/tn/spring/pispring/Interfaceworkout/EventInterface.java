package tn.spring.pispring.Interfaceworkout;

import tn.spring.pispring.Entities.Event;
import tn.spring.pispring.Entities.Exercise;
import tn.spring.pispring.Entities.Participation;

import java.util.List;
import java.util.Optional;

public interface EventInterface {
    Event creatEvent(Event event);
    Event updatEvent(Event event);
    void deleteEvent(Long id_event);
    List<Event> getAllEvent();
    Event getEventById(Long id_event);
    Event updateEventMaxParticipation(Long id_event, int newMaxParticipation);
    Optional<Event> getEventByTitre(String titre);


}
