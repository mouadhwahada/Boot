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
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Workoutprogram implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String duration ;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "workoutprograms")

    private List<Exercise> exercises;
    @OneToMany(mappedBy = "workoutprogramss")
    @ToString.Exclude
    @JsonIgnore
    private  List<FollowedProgram> followedPrograms;
}
