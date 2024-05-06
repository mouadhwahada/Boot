package tn.spring.pispring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.spring.pispring.Entities.Feedback;
import tn.spring.pispring.Entities.Orderr;
import tn.spring.pispring.Entities.User;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

}
