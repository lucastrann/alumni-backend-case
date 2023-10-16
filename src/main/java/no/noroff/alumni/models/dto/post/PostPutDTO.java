package no.noroff.alumni.models.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPutDTO {
    private String title;
    private String content;
}