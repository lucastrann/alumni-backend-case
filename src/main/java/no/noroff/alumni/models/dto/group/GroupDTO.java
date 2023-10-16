package no.noroff.alumni.models.dto.group;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupDTO {
    private int id;
    private String name;
    private String description;
    private String color;
    private boolean isPrivate;
}