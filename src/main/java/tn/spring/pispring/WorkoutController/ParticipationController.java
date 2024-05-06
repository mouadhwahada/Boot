package tn.spring.pispring.WorkoutController;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Event;
import tn.spring.pispring.Entities.Participation;
import tn.spring.pispring.Interfaceworkout.ParticipationInterface;

import tn.spring.pispring.dto.ParticipationDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/participation")
@AllArgsConstructor
public class ParticipationController {
    @Autowired
    private ParticipationInterface participationInterface;

    @PostMapping("/{idUser}/{idEvent}")
    public ResponseEntity<Participation> createParticipation(@RequestBody Participation participation,
                                                             @PathVariable Long idUser, @PathVariable Long idEvent) {

        return ResponseEntity.ok(participationInterface.creatParticipation(participation, idUser, idEvent));

    }
    @PutMapping("/update")
    public ResponseEntity updatParticipation(@RequestBody Participation updatedParticipation) {
        Long participationId = updatedParticipation.getId(); // Extraire l'identifiant de la participation de l'objet mis à jour
        if (participationId == null) {
            return new ResponseEntity<>("Participation ID cannot be null", HttpStatus.BAD_REQUEST);
        }

        Participation updated = participationInterface.updatParticipation(updatedParticipation);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update participation", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipation(@PathVariable Long id) {
        participationInterface.deleteParticipation(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Participation>> getParticipations() {
        return ResponseEntity.ok(participationInterface.getAllParticipation());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participation> getParticipationsById(@PathVariable Long id) {
        return ResponseEntity.ok(participationInterface.getParticipationById(id));
    }

    @GetMapping("/event/{idEvent}")
    public ResponseEntity<List<Participation>> getParticipationsByEvent(@PathVariable Long idEvent) {
        return ResponseEntity.ok(participationInterface.getParticipationsByEvent(idEvent));
    }

    @GetMapping("/user/{idUser}/participations")
    public ResponseEntity<List<ParticipationDTO>> getParticipationsByUser(@PathVariable Long idUser){
        return ResponseEntity.ok(participationInterface.getParticipationsByUser(idUser));
    }
    /*
@GetMapping("/user/{idUser}/participations")
public ResponseEntity<List<Map<String, Object>>> getParticipationsByUser(@PathVariable Long idUser) {
    List<Participation> participations = participationInterface.getParticipationsByUser(idUser);

    List<Map<String, Object>> participationMaps = participations.stream()
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

    return ResponseEntity.ok(participationMaps);
}
*/
    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<Event>> getEventsByUser(@PathVariable Long idUser) {
        List<Event> events = participationInterface.getEventsByUser(idUser);
        return ResponseEntity.ok(events);
    }
}


