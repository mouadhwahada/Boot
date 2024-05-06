package tn.spring.pispring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tn.spring.pispring.dto.RoleName;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60) // Assure la longueur de la colonne
    @Enumerated(EnumType.STRING) // Permet d'enregistrer le nom de l'énumération
    private RoleName name;

    @OneToMany(mappedBy = "role")
    @JsonIgnore // Ignorer lors de la sérialisation JSON
    private List<User> users;
}
