package no.noroff.alumni.models.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPostDTO {
    private String title;
    private String content;
    private String postTarget;
    private String senderId;
    private int replyParentId;
    private String targetUser;
    private int targetGroup;
    private int targetTopic;
}