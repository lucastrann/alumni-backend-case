package no.noroff.alumni.mappers;

import no.noroff.alumni.models.Groups;
import no.noroff.alumni.models.Post;
import no.noroff.alumni.models.Users;
import no.noroff.alumni.models.dto.group.GroupMiniDTO;
import no.noroff.alumni.models.dto.post.PostDTO;
import no.noroff.alumni.models.dto.post.PostPostDTO;
import no.noroff.alumni.models.dto.post.PostPutDTO;
import no.noroff.alumni.models.dto.users.SenderDTO;
import no.noroff.alumni.services.group.GroupService;
import no.noroff.alumni.services.post.PostService;
import no.noroff.alumni.services.users.UsersService;import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UsersService.class, GroupService.class, PostService.class})
public abstract class PostMapper {

    @Autowired
    protected UsersService usersService;
    @Autowired
    protected GroupService groupService;
    @Autowired
    protected PostService postService;

    @Mapping(target = "targetGroup", source = "targetGroup", qualifiedByName = "groupToMiniDTO")
    @Mapping(target = "targetUser", source = "targetUser", qualifiedByName = "targetUserToSenderDTO")
    @Mapping(target = "senderId", source = "senderId", qualifiedByName = "userToSenderDTO")
    @Mapping(target = "replyParentId", source = "replyParentId.id")
    @Mapping(target = "replies", source = "replies", qualifiedByName = "postsToPostId")
    @Mapping(target = "originId", source = "origin.id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    public abstract PostDTO postToPostDTO(Post post);

    public abstract Collection<PostDTO> postToPostDTO(Collection<Post> posts);

    public abstract Post postPutDTOToPost(PostPutDTO postPutDTO);

    @Mapping(target = "senderId", source = "senderId")
    @Mapping(target = "replyParentId", source = "replyParentId", qualifiedByName = "parentPostIdToPost")
    @Mapping(target = "targetUser", source = "targetUser", qualifiedByName = "userIdToUser")
    @Mapping(target = "targetGroup", source = "targetGroup", qualifiedByName = "groupIdToGroup")
    public abstract Post postPostDTOToPost(PostPostDTO postPostDTO);

    @Named(value = "userToSenderDTO")
    SenderDTO mapSend(Users value){
        if(value == null)
            return null;
        SenderDTO sender = new SenderDTO();
        sender.setId(value.getId());
        sender.setName(value.getName());
        sender.setPicture(value.getPicture());
        return sender;
    }

    @Named(value = "targetUserToSenderDTO")
    SenderDTO mapTargetUser(Users user){
        if(user == null)
            return null;
        SenderDTO targetUser = new SenderDTO();
        targetUser.setId(user.getId());
        targetUser.setName(user.getName());
        targetUser.setPicture(user.getPicture());
        return targetUser;
    }

    @Named(value = "groupToMiniDTO")
    GroupMiniDTO mapMiniGroup(Groups group){
        if(group == null)
            return null;
        GroupMiniDTO miniGroup = new GroupMiniDTO();
        miniGroup.setId(group.getId());
        miniGroup.setName((group.getName()));
        return miniGroup;
    }


    @Named(value = "userIdToUser")
    Users map(String value) {
        try {
            return usersService.findById(value);
        } catch (Exception e) {
            return null;
        }
    }

    @Named(value = "groupIdToGroup")
    Groups maps(Integer value) {
        try {
            return groupService.findById(value);
        } catch (Exception e) {
            return null;
        }
    }

    @Named(value = "parentPostIdToPost")
    Post maper(Integer value) {
        try {
            return postService.findById(value);
        } catch (Exception e) {
            return null;
        }
    }

    @Named(value = "postsToPostId")
    Set<Integer> map(Set<Post> value) {
        if(value == null)
            return new HashSet<>();
        return value.stream()
                .map(Post::getId)
                .collect(Collectors.toSet());
    }

    java.time.ZonedDateTime timeMap(Instant instant){
        return instant == null ? null : ZonedDateTime.from(instant);
    }
}