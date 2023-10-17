package no.noroff.alumni.services.topic;

import no.noroff.alumni.models.Topic;
import no.noroff.alumni.services.CRUDService;

import java.util.Set;

public interface TopicService extends CRUDService<Topic, Integer> {
    Set<Topic> searchResultsWithLimitOffset(String search, int offset, int limit);
    Topic addUserToTopic(String userId, int topicId);
    Topic removeUserFromTopic(String userId, int topicId);
    Set<Topic> findTopicsWithUser(String userId);
}