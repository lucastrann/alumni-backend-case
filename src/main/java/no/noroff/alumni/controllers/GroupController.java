package no.noroff.alumni.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.alumni.mappers.GroupMapper;
import no.noroff.alumni.mappers.UsersMapper;
import no.noroff.alumni.models.Groups;
import no.noroff.alumni.models.Users;
import no.noroff.alumni.models.dto.group.GroupDTO;
import no.noroff.alumni.models.dto.group.GroupPostDTO;
import no.noroff.alumni.models.dto.group.GroupPutDTO;
import no.noroff.alumni.models.dto.users.UsersDTO;
import no.noroff.alumni.services.group.GroupService;
import no.noroff.alumni.services.users.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/group")
public class GroupController {
    private final GroupService groupService;
    private final GroupMapper groupMapper;
    private final UsersService usersService;
    private final UsersMapper usersMapper;

    public GroupController(GroupService groupService, GroupMapper groupMapper, UsersService usersService, UsersMapper usersMapper) {
        this.groupService = groupService;
        this.groupMapper = groupMapper;
        this.usersService = usersService;
        this.usersMapper = usersMapper;
    }

    @GetMapping("{id}")
    @Operation(summary = "Get a group by its id", tags = {"Group", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GroupDTO.class))),
            @ApiResponse(responseCode = "404", description = "Group not found", content = @Content)
    })
    public ResponseEntity<GroupDTO> findById( @PathVariable int id) {
        if (!groupService.exists(id))
            return ResponseEntity.notFound().build();
        String userId = "lucas";
        GroupDTO group = groupMapper.groupToGroupDTO(groupService.findByIdWhereUserHasAccess(userId, id));
        if (group == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(group);
    }

    @GetMapping
    @Operation(summary = "Get all groups", tags = {"Group", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = GroupDTO.class)))})
    })
    public ResponseEntity<Collection<GroupDTO>> findAll( @RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset) {
        String userId = "lucas";
        return ResponseEntity.ok(groupMapper.groupToGroupDTO(
                groupService.searchResultsWithLimitOffset(userId, search.orElse("").toLowerCase(), offset.orElse(0), limit.orElse(99999999))));
    }

    @PostMapping
    @Operation(summary = "Add a group", tags = {"Group", "Post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content)
    })
    public ResponseEntity<Object> add(@RequestBody GroupPostDTO entity) {
        Groups group = groupMapper.groupPostDTOToGroup(entity);
        String id = "lucas";
        Set<Users> user = new HashSet<>();
        user.add(usersService.findById(id));
        group.setUsers(user);
        groupService.add(group);
        URI uri = URI.create("api/v1/group/" + group.getId());
        return ResponseEntity.created(uri).body(group.getId());
    }

    @PutMapping("{id}")
    @Operation(summary = "Update a group", tags = {"Group", "Put"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Group updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, URI does not match request body", content = @Content),
            @ApiResponse(responseCode = "404", description = "Group not found", content = @Content)
    })
    public ResponseEntity<Object> update( @RequestBody GroupPutDTO entity, @PathVariable int id) {
        if (!groupService.exists(id))
            return ResponseEntity.badRequest().build();
        if (!groupService.checkIfUserInGroup("lucas", id))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        Groups group = groupMapper.groupPutDTOToGroup(entity);
        Groups oldGroup = groupService.findById(id);
        group.setId(id);
        group.setUsers(oldGroup.getUsers());
        groupService.update(group);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/join")
    @Operation(summary = "Add a user to a group", tags = {"Group", "Users", "Post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content),
            @ApiResponse(responseCode = "401", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<Object> addUserToGroup( @PathVariable int id, @RequestParam Optional<String> user) {
        if (!groupService.exists(id))
            return ResponseEntity.badRequest().build();

        boolean privateGroup = groupService.findById(id).isPrivate();
        if (privateGroup) {
            if (!groupService.checkIfUserInGroup("lucas", id))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String userId = user.orElse("");
        if (userId.equals("")) {
            userId = "lucas";
        }
        groupService.addUserToGroup(userId, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/leave")
    @Operation(summary = "Remove a user from a group", tags = {"Group", "Users", "Put"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Group updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, URI does not match request body", content = @Content),
            @ApiResponse(responseCode = "404", description = "Group not found", content = @Content)
    })
    public ResponseEntity<Object> removeUserFromGroup( @PathVariable int id) {
        if (!groupService.exists(id))
            return ResponseEntity.badRequest().build();

        String userId = "lucas";
        groupService.removeUserFromGroup(userId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user")
    @Operation(summary = "Get all groups for a user", tags = {"Group", "Users", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = GroupDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Group not found", content = @Content)
    })
    public ResponseEntity<Collection<GroupDTO>> findGroupsForAUser() {
        String userId = "lucas";
        return ResponseEntity.ok(groupMapper.groupToGroupDTO(groupService.findGroupsWithUser(userId)));
    }

    @GetMapping("{id}/user/list")
    @Operation(summary = "Get all users in a group", tags = {"Group", "Users", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = UsersDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Group not found", content = @Content)
    })
    public ResponseEntity<Collection<UsersDTO>> findAllMembers(@PathVariable int id) {
        Groups groups = groupService.findById(id);
        return ResponseEntity.ok(usersMapper.usersToUsersDTO(groups.getUsers()));
    }
}