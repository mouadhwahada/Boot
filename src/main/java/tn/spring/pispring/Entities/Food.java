package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import tn.spring.pispring.dto.AbonnementDto;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Food implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFood;
    private String namefood;
    private Long calories_per_serving ;
    private Long  protein_per_serving;
    private Long  carbohydrates_per_Serving;
    private Long  fat_per_Serving;
    private Long  fiber_per_Serving;
    private Long  vitamins_per_Serving;
    private Long  minerals_per_Serving;

    @ManyToOne
    private NutritionTracking nuttrack;
}
