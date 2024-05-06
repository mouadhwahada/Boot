package tn.spring.pispring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;
    private Date registrationDate;
    private String injury;
    private String emergencyNumber;
}


