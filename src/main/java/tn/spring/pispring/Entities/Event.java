package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private Date eventDate;
    private int duration;
    private int maxParticiaption;
    private String image;
    private double longitude;
    private double latitude;
    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)

    private List<Participation> participatios;
}
