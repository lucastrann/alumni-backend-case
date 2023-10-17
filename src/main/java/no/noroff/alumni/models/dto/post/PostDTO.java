package no.noroff.alumni.models.dto.post;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PostDTO {
    private int id;
    private String title;
    private String content;
    private int replyParentId;
    private Set<Integer> replies;
}
