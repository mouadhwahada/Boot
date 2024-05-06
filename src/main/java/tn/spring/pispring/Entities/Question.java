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
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idQuestion;
    @JsonProperty("charQ")
    String charQ;
    @JsonProperty("textQ")
    String textQ;
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "question")
    List<Answer> answerList;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    @JsonProperty("quiz")
    Quiz quiz;

}
