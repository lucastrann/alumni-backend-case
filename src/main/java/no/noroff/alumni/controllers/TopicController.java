package no.noroff.alumni.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.alumni.mappers.TopicMapper;
import no.noroff.alumni.mappers.UsersMapper;
import no.noroff.alumni.models.Topic;
import no.noroff.alumni.models.Users;
import no.noroff.alumni.models.dto.topic.TopicDTO;
import no.noroff.alumni.models.dto.topic.TopicPostDTO;
import no.noroff.alumni.models.dto.topic.TopicPutDTO;
import no.noroff.alumni.models.dto.users.UsersDTO;
import no.noroff.alumni.services.topic.TopicService;
import no.noroff.alumni.services.users.UsersService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping(path = "api/v1/topic")
public class TopicController {
    private final TopicService topicService;
    private final TopicMapper topicMapper;
    private final UsersService usersService;
    private final UsersMapper usersMapper;

    public TopicController(TopicService topicService, TopicMapper topicMapper, UsersService usersService, UsersMapper usersMapper) {
        this.topicService = topicService;
        this.topicMapper = topicMapper;
        this.usersService = usersService;
        this.usersMapper = usersMapper;
    }

    /*@GetMapping("{id}")
    @Operation(summary = "Get a topic by its id", tags = {"Topic", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Topic not found", content = @Content)
    })
    public ResponseEntity<TopicDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(topicMapper.topicToTopicDTO(topicService.findById(id)));
    }

    /*@GetMapping
    @Operation(summary = "Get all topics", tags = {"Topic", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<Collection<TopicDTO>> findAll(@RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset) {
        return ResponseEntity.ok(topicMapper.topicToTopicDTO(
                topicService.searchResultsWithLimitOffset(search.orElse("").toLowerCase(), offset.orElse(0), limit.orElse(99999999))));
    }

    /*@PostMapping
    @Operation(summary = "Add a topic", tags = {"Topic", "Post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content)
    })
    public ResponseEntity<Object> add(@RequestBody TopicPostDTO entity, Principal principal) {
        Topic topic = topicMapper.topicPostDTOToTopic(entity);
        String id = principal.getName();
        Set<Users> user = new HashSet<>();
        user.add(usersService.findById(id));
        topic.setUsers(user);

        topicService.add(topic);
        URI uri = URI.create("api/v1/topic/" + topic.getId());
        return ResponseEntity.created(uri).body(topic.getId());
    }*/

   /* @PutMapping("{id}")
    @Operation(summary = "Update a topic", tags = {"Topic", "Put"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Topic updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, URI does not match request body", content = @Content),
            @ApiResponse(responseCode = "404", description = "Topic not found", content = @Content)
    })
    public ResponseEntity<Object> update(@RequestBody TopicPutDTO entity, @PathVariable int id) {
        if (!topicService.exists(id))
            return ResponseEntity.badRequest().build();

        Topic topic = topicMapper.topicPutDTOToTopic(entity);
        topic.setId(id);
        topic.setCreatedAt(topicService.findById(id).getCreatedAt());
        topicService.update(topic);
        return ResponseEntity.noContent().build();
    }*/

    /*@DeleteMapping("{id}")
    @Operation(summary = "Delete a topic by its id", tags = {"Topic", "Delete"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Topic deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Topic not found", content = @Content)
    })
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        topicService.deleteById(id);
        return ResponseEntity.noContent().build();
    }*/

    /*@PostMapping("{id}/join")
    @Operation(summary = "Add a user to a topic", tags = {"Topic", "Users", "Post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content),
            @ApiResponse(responseCode = "404", description = "Topic not found", content = @Content)
    })
    public ResponseEntity<Object> addUserToTopic(Principal principal, @PathVariable int id) {
        if (!topicService.exists(id))
            return ResponseEntity.badRequest().build();
        String userId = principal.getName();
        topicService.addUserToTopic(userId, id);
        return ResponseEntity.noContent().build();
    }*/

    /*@PutMapping("{id}/leave")
    @Operation(summary = "Update a topic", tags = {"Topic", "Users", "Put"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User removed", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, URI does not match request body", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<Object> removeUserFromTopic(Principal principal, @PathVariable int id) {
        if (!topicService.exists(id))
            return ResponseEntity.badRequest().build();
        String userId = principal.getName();
        topicService.removeUserFromTopic(userId, id);
        return ResponseEntity.noContent().build();
    }*/

    /*@GetMapping("/user")
    @Operation(summary = "Get all topics for a user", tags = {"Topic", "Users", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = TopicDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Topics not found", content = @Content)
    })
    public ResponseEntity<Collection<TopicDTO>> findTopicsForAUser(Principal principal) {
        String userId = principal.getName();
        return ResponseEntity.ok(topicMapper.topicToTopicDTO(topicService.findTopicsWithUser(userId)));
    }*/

    /*@GetMapping("/{id}/user/list")
    @Operation(summary = "Get all users in a topic", tags = {"Topic", "Users", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = UsersDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Topic not found", content = @Content)
    })
    public ResponseEntity<Collection<UsersDTO>> findMembersOfTopic(@PathVariable int id) {
        Topic topic = topicService.findById(id);
        return ResponseEntity.ok(usersMapper.usersToUsersDTO(topic.getUsers()));
    }*/
}