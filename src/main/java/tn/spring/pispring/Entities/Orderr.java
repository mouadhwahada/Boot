package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


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
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Orderr implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idOrder;
    @Temporal(TemporalType.DATE)
    public Date dateOrder;
    public OrderStatus status;
    public float costOrder;
    public String codeOrder; // Champ pour le code de commande

    private String codeQR;
    private boolean confirmedByDeliveryMan;
    private boolean confirmedByClient;

    @OneToMany(mappedBy = "orderr",cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    public List<OrderItem> orderItems;


    @ManyToOne

    User user;
    @ManyToOne
    @JoinColumn(name = "delivery_man_id")
    private User deliveryMan; // Livreur assigné à la commande


    @OneToOne(mappedBy = "orderr")
    @ToString.Exclude
    @JsonIgnore
    private Delivery delivery;



}

