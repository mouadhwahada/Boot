package tn.spring.pispring.ServiceIMP;

import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Feedback;
import tn.spring.pispring.Entities.Orderr;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.repo.FeedbackRepository;
import tn.spring.pispring.repo.OrderRepository;
import tn.spring.pispring.repo.Orderrepo;
import tn.spring.pispring.repo.UserRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    UserRepository userRepo;
    @Autowired
    Orderrepo orderRepository;

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Optional<Feedback> getFeedbackById(int id) {
        return feedbackRepository.findById(id);
    }

    public Feedback createFeedback( long userId,int rating, String orderCode) {

User user=userRepo.findById(userId).orElse(null);
        Orderr orderr = orderRepository.findByCodeOrder(orderCode);


        Feedback feedback = new Feedback();

        feedback.setRating(rating);
        feedback.setOrderr(orderr);
        List<User> users = new ArrayList<>();
        users.add(user);
        feedback.setUsers(users);


        return feedbackRepository.save(feedback);
    }

    public double calculateAverageRating() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        if (feedbacks.isEmpty()) {
            return 0;
        } else {
            double totalRating = 0;
            for (Feedback feedback : feedbacks) {
                totalRating += feedback.getRating();
            }
            return totalRating / feedbacks.size();
        }
    }



}
