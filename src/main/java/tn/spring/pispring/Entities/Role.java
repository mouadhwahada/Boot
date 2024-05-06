package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import tn.spring.pispring.dto.RoleName;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(length = 60) // Assure la longueur de la colonne
    @Enumerated(EnumType.STRING) // Permet d'enregistrer le nom de l'énumération
    private RoleName name;




}
