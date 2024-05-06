package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import tn.spring.pispring.dto.AbonnementDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Quiz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idQuiz;
    @JsonProperty("titleQuiz")
    String titleQuiz;
    @JsonProperty("topicQuiz")
    String topicQuiz;
    @OneToMany(mappedBy = "quiz")
    @JsonIgnore
    @ToString.Exclude
    List<Question> questionList;
}
