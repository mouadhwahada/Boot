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
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int idProduct;
    public String name;
    public Float price;
    public String description;
    public int stockQuantity;
    @Enumerated(EnumType.STRING)
    public TypeProduit type;
    public boolean isfavourite=false;
    public String image;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;


    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    @JsonIgnore

    private List<OrderItem> orderItems;


}
