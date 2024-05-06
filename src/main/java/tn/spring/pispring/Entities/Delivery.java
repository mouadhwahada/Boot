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
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Delivery implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int deliveryId;

    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    private String deliveryAddress;
    private String deliveryPostalCode;
    private String deliveryCity;


    @OneToOne
    private Orderr orderr;


    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    User user;
}
