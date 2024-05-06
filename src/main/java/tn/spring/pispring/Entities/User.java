package tn.spring.pispring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastname;

    private String username;
    private String email;
    private String password;
    private String address;
    private int phoneNumber;
    private int number;
    private boolean blocked ;
    private boolean valid ;
    private String token;
    private String image;

    private Date datenaissance;
    private Float weight;
    private Float hight;
    @Enumerated(EnumType.STRING)
    private Objectif objectif;
    private Float imc;
    private  String Zone;


    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    //@JsonIgnore

    private Set <Role> roles = new HashSet<>();


    public User(String name,String lastname, String username, String email, String password, boolean blocked, String address, boolean valid) {

        this.name = name;
        this.lastname=lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.blocked = blocked;
        this.address = address;
        this.valid = valid;

    }


    @ManyToOne

    Role role;


    @OneToOne
    @ToString.Exclude
    @JsonIgnore
    private Fidelity fidelity;


    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private NutritionalGoal nutritionalGoal;



    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @JsonIgnore
    public List<Orderr> orderrs;


    @OneToMany(mappedBy = "userworkout")
    @ToString.Exclude
    @JsonIgnore
    private  List<FollowedProgram> followedProgramsuser;


//    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    //  private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Commentaire> commentaires;


}
