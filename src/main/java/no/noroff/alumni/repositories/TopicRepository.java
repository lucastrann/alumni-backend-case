package no.noroff.alumni.repositories;

import no.noroff.alumni.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    @Query(value = "select * from Topic t where (lower(t.name) like (%?1%) or (lower(t.description) like (%?1%))) limit ?2 offset ?3", nativeQuery = true)
    Set<Topic> findTopicsByNameWithLimitOffset(String search, int limit, int offset);

    @Query(value = "SELECT * FROM topic WHERE id IN (SELECT topic_id FROM topic_user WHERE users_id = ?1) ORDER BY name ASC", nativeQuery = true)
    Set<Topic> findTopicsAUserIsIn(String userId);
}