package no.noroff.alumni.repositories;

import no.noroff.alumni.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "select * from Post p where p.parent_id is null and p.topic_id = ?1 and ((lower(p.title) like (%?2%) or (lower(p.content) like (%?2%)))) ORDER BY created_at DESC limit ?3 offset ?4", nativeQuery = true)
    Set<Post> findPostsInATopicWithSearchLimitOffset(int topicId, String search, int limit, int offset);

    @Query(value = "select * from Post p where p.parent_id is null and p.group_id = ?1 and ((lower(p.title) like (%?2%) or (lower(p.content) like (%?2%)))) ORDER BY created_at DESC limit ?3 offset ?4", nativeQuery = true)
    Set<Post> findPostsInAGroupWithSearchLimitOffset(int topicId, String search, int limit, int offset);

    @Query(value = "select * from Post p where p.parent_id is null and (p.users_id = ?1 or (p.owner_id = ?1 and users_id IS NOT null)) and ((lower(p.title) like (%?2%) or (lower(p.content) like (%?2%)))) ORDER BY created_at DESC limit ?3 offset ?4 ", nativeQuery = true)
    Set<Post> findPostsToUserWithSearchLimitOffset(String userId, String search, int limit, int offset);

    @Query(value = "select * from Post p where p.users_id = ?1 and p.owner_id = ?2 and ((lower(p.title) like (%?3%) or (lower(p.content) like (%?3%)))) ORDER BY created_at DESC limit ?4 offset ?5", nativeQuery = true)
    Set<Post> findPostsToUserFromSpecificUserWithSearchLimitOffset(String userId, String senderId, String search, int limit, int offset);

    @Query(value = "SELECT * FROM post WHERE parent_id IS NULL AND (group_id IN (SELECT groups_id FROM group_user WHERE users_id = ?1) OR topic_id IN (SELECT topic_id  FROM topic_user WHERE users_id = ?1)) AND ((lower(title) like (%?2%) or (lower(content) like (%?2%)))) ORDER BY created_at DESC limit ?3 offset ?4", nativeQuery = true)
    Set<Post> findPostsThatUserSubscribesToWithLimitOffset(String userId, String search, int limit, int offset);

    @Query(value = "SELECT * FROM post WHERE (group_id IN (SELECT groups_id FROM group_user WHERE users_id = ?1) OR topic_id IN (SELECT topic_id  FROM topic_user WHERE users_id = ?1)) AND ((lower(title) like (%?2%) or (lower(content) like (%?2%)))) ORDER BY created_at DESC limit ?3 offset ?4", nativeQuery = true)
    Set<Post> findPostsThatUserSubscribesToWithSearchLimitOffset(String userId, String search, int limit, int offset);

    @Query(value = "SELECT * FROM post WHERE topic_id IN (SELECT topic_id FROM topic_user WHERE users_id = ?1) AND parent_id IS NULL AND ((lower(title) like (%?2%) or (lower(content) like (%?2%)))) ORDER BY created_at DESC limit ?3 offset ?4", nativeQuery = true)
    Set<Post> findAllPostsFromATopicUserIsSubscribedTo(String userId, String search, int limit, int offset);

    @Query(value = "SELECT * FROM post WHERE group_id IN (SELECT groups_id FROM group_user WHERE users_id = ?1) AND parent_id IS NULL AND ((lower(title) like (%?2%) or (lower(content) like (%?2%))))  ORDER BY created_at DESC limit ?3 offset ?4", nativeQuery = true)
    Set<Post> findAllPostsFromAGroupUserIsSubscribedTo(String userId, String search, int limit, int offset);
}