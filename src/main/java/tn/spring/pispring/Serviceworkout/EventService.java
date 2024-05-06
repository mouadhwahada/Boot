package tn.spring.pispring.Serviceworkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Event;
import tn.spring.pispring.Interfaceworkout.EventInterface;
import tn.spring.pispring.repositoriesworkout.EventRepository;


import java.util.List;
import java.util.Optional;

@Service
public class EventService implements EventInterface {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event creatEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updatEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id_event) {
        eventRepository.deleteById(id_event);
    }

    @Override
    public List<Event> getAllEvent() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long id_event) {
        return eventRepository.findById(id_event).orElse(null);
    }


    @Override
    public Event updateEventMaxParticipation(Long eventId, int newMaxParticipation) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        return optionalEvent.map(eventToUpdate -> {
            eventToUpdate.setMaxParticiaption(newMaxParticipation);
            return eventRepository.save(eventToUpdate);
        }).orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
    }

    @Override
    public Optional<Event> getEventByTitre(String titre) {
        return eventRepository.findByTitre(titre);

    }

}
