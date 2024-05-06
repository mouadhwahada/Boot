package tn.spring.pispring.WorkoutController;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Event;
import tn.spring.pispring.Entities.Workoutprogram;
import tn.spring.pispring.Interfaceworkout.EventInterface;
import tn.spring.pispring.Serviceworkout.EventService;
import tn.spring.pispring.repositoriesworkout.EventRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/event")
@AllArgsConstructor
public class EventController {

    @Autowired
    private EventInterface eventInterface;

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event){
        return ResponseEntity.ok(eventInterface.creatEvent(event));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        eventInterface.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(){
        return ResponseEntity.ok(eventInterface.getAllEvent());
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> getEventTitleById(@PathVariable Long id) {
        Event event = eventInterface.getEventById(id);
        if (event != null) {
            return ResponseEntity.ok(event.getTitre()); // Retourne uniquement le titre de l'événement
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/updatEvent")
    public ResponseEntity<Event> updatEvent(@RequestBody Event event){
        return ResponseEntity.ok(eventInterface.creatEvent(event));
    } @PutMapping("/{id_event}/maxParticipation")
    public ResponseEntity<Event> updateMaxParticipation(@PathVariable("id_event") Long id_event, @RequestParam("newMaxParticipation") int newMaxParticipation) {
        Event updatedEvent = eventInterface.updateEventMaxParticipation(id_event, newMaxParticipation);
        return ResponseEntity.ok(updatedEvent);
    }
    @GetMapping("/titre/{titre}")
    public ResponseEntity<Event> getEventByTitre(@PathVariable String titre) {
        Optional<Event> event = eventInterface.getEventByTitre(titre);
        if (event.isPresent()) {
            return new ResponseEntity<>(event.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}



