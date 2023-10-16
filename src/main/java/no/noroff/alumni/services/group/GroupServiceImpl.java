package no.noroff.alumni.services.group;

import no.noroff.alumni.expections.GroupNotFoundException;
import no.noroff.alumni.models.Groups;
import no.noroff.alumni.repositories.GroupRepository;
import no.noroff.alumni.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final UsersRepository usersRepository;

    public GroupServiceImpl(GroupRepository groupRepository, UsersRepository usersRepository) {
        this.groupRepository = groupRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public Groups findById(Integer id) {
        return groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
    }

    @Override
    public Collection<Groups> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Groups add(Groups entity) {
        return groupRepository.save(entity);
    }

    @Override
    public Groups update(Groups entity) {
        return groupRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        groupRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return groupRepository.existsById(id);
    }

    @Override
    public Set<Groups> searchResultsWithLimitOffset(String userId, String search, int offset, int limit) {
        return groupRepository.findGroupsByNameWithLimitOffset(userId, search, limit, offset);
    }

    @Override
    public Groups addUserToGroup(String userId, int groupId) {
        Groups group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));
        group.getUsers().add(usersRepository.findById(userId).get());

        return groupRepository.save(group);
    }

    @Override
    public Groups removeUserFromGroup(String userId, int groupId) {
        Groups group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));
        boolean executed = group.getUsers().remove(usersRepository.findById(userId).get());
        if(!executed)
            return group;

        return groupRepository.save(group);
    }

    @Override
    public Set<Groups> findGroupsWithUser(String userId) {
        return groupRepository.findGroupsAUserIsIn(userId);
    }

    @Override
    public Groups findByIdWhereUserHasAccess(String userId, int groupId) {
        return groupRepository.findGroupByIdIfUserHasAccess(userId, groupId);
    }

    @Override
    public boolean checkIfUserInGroup(String userId, int groupId) {
        return groupRepository.checkIfUserIsInGroup(userId, groupId);
    }
}