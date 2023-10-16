package no.noroff.alumni.models.dto.group;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupPostDTO {
    private String name;
    private String description;
    private String color;
    private boolean isPrivate;
}