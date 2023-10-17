package no.noroff.alumni.models.dto.topic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicDTO {
    private int id;
    private java.time.ZonedDateTime createdAt;
    private java.time.ZonedDateTime updatedAt;
    private String name;
    private String description;
    private String color;
}