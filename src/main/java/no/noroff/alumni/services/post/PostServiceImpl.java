package no.noroff.alumni.services.post;

import no.noroff.alumni.expections.PostNotFoundException;
import no.noroff.alumni.models.Post;
import no.noroff.alumni.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post findById(Integer id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    }

    @Override
    public Collection<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post add(Post entity) {
        return postRepository.save(entity);
    }

    @Override
    public Post update(Post entity) {
        return postRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return postRepository.existsById(id);
    }

    @Override
    public Set<Post> findAllPostsInTopic(int id, String search, int limit, int offset) {
        return postRepository.findPostsInATopicWithSearchLimitOffset(id, search, limit, offset);
    }

    @Override
    public Set<Post> findAllPostsInGroup(int id, String search, int limit, int offset) {
        return postRepository.findPostsInAGroupWithSearchLimitOffset(id, search, limit, offset);
    }

    @Override
    public Set<Post> findAllPostsToUser(String id, String search, int limit, int offset) {
        return postRepository.findPostsToUserWithSearchLimitOffset(id, search, limit, offset);
    }

    @Override
    public Set<Post> findAllPostsToUserFromSpecificUser(String id, String senderId, String search, int limit, int offset) {
        return postRepository.findPostsToUserFromSpecificUserWithSearchLimitOffset(id, senderId, search, limit, offset);
    }

    @Override
    public Set<Post> findPostsUserSubscribedTo(String id, String search, int limit, int offset) {
        if(search == "")
            return postRepository.findPostsThatUserSubscribesToWithLimitOffset(id, search, limit, offset);
        return postRepository.findPostsThatUserSubscribesToWithSearchLimitOffset(id, search, limit, offset);
    }

    @Override
    public Set<Post> findPostsFromTopicUserIsSubscribedTo(String id, String search, int limit, int offset) {
        return postRepository.findAllPostsFromATopicUserIsSubscribedTo(id, search, limit, offset);
    }

    @Override
    public Set<Post> findPostsFromGroupUserIsSubscribedTo(String id, String search, int limit, int offset) {
        return postRepository.findAllPostsFromAGroupUserIsSubscribedTo(id, search, limit, offset);
    }
}