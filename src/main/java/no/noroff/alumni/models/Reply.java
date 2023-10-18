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
public class Reply {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String picture;
    @Column
    private String status;
    @Column
    private String bio;
    @Column(name = "fun_fact")
    private String funFact;
    @ManyToMany(mappedBy = "users")
    private Set<Groups> groups;

    // This is the users "DM" posts.
    @OneToMany(mappedBy = "targetUser")
    private Set<Post> posts;

    // This is the users own posts
    @OneToMany(mappedBy = "senderId")
    private Set<Post> posted;

}