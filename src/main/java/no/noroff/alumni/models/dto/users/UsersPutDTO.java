package no.noroff.alumni.models.dto.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersPutDTO {
    private String name;
    private String picture;
    private String status;
    private String bio;
    private String funFact;
}