package tn.spring.pispring.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationDTO{
    private Long id;

    private Long idUser;
    private Long idEvent;
    private Date registrationDate;
    private String injury;
    private String emergencyNumber;
}
