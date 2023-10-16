package no.noroff.alumni.services.post;

import no.noroff.alumni.models.Post;
import no.noroff.alumni.services.CRUDService;

import java.util.Set;

public interface PostService extends CRUDService<Post, Integer> {
    Set<Post> findAllPostsInTopic(int id, String search, int limit, int offset);
    Set<Post> findAllPostsInGroup(int id, String search, int limit, int offset);
    Set<Post> findAllPostsToUser(String id, String search, int limit, int offset);
    Set<Post> findAllPostsToUserFromSpecificUser(String id, String senderId, String search, int limit, int offset);
    Set<Post> findPostsUserSubscribedTo(String id, String search, int limit, int offset);
    Set<Post> findPostsFromTopicUserIsSubscribedTo(String id, String search, int limit, int offset);
    Set<Post> findPostsFromGroupUserIsSubscribedTo(String id, String search, int limit, int offset);
}