package no.noroff.alumni.models.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyDTO {
    private String userId;
    private String replyContent;
}