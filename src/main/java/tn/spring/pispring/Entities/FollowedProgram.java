package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import tn.spring.pispring.dto.AbonnementDto;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class FollowedProgram implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String weightGoal;
    private String steps;
    private String duration;
    private String height;
    private String Neck;
    private String Waist;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    Workoutprogram  workoutprogramss ;


    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    User userworkout;
}
