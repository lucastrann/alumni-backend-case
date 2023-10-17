package no.noroff.alumni.models.dto.topic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicPutDTO {
    private String name;
    private String description;
    private String color;
}