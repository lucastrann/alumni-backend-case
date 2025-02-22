package no.noroff.alumni.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Set;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private java.time.ZonedDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private java.time.ZonedDateTime updatedAt;
    @Column
    private String title;
    @Column(length = 4000)
    private String content;
    @Column(name = "post_target")
    private String postTarget;
    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Post origin;
    @OneToMany(mappedBy = "origin")
    private Set<Post> thread;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Users senderId;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Post replyParentId;
    @OneToMany(mappedBy = "replyParentId")
    private Set<Post> replies;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users targetUser;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups targetGroup;


}