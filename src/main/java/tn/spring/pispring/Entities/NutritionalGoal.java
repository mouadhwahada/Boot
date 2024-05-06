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
public class NutritionalGoal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNGoal;

    private Long daily_calorie_goal;
    private Long daily_protein_goal;
    private Long daily_carbohydrates_goal;
    private String goal;
    private float weight_goal;
    private int Duration;


    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "nutritionalGoal")
    private List<User> userList;

    @OneToMany(mappedBy = "nutritiongoal")
    private List<NutritionTracking> NuttrackingList;
}
