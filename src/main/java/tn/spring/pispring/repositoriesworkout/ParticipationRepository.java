package tn.spring.pispring.repositoriesworkout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Event;
import tn.spring.pispring.Entities.Participation;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation,Long> {
    @Query("SELECT p.event FROM Participation p WHERE p.user.id = :idUser")
    List<Event> findEventsByUserId(Long idUser);

}
