package no.noroff.alumni.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.alumni.mappers.UsersMapper;
import no.noroff.alumni.models.Users;
import no.noroff.alumni.models.dto.users.UsersDTO;
import no.noroff.alumni.models.dto.users.UsersMiniDTO;
import no.noroff.alumni.models.dto.users.UsersPutDTO;
import no.noroff.alumni.services.users.UsersService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.Base64;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/user")
public class UsersController {
    private final UsersService usersService;
    private final UsersMapper usersMapper;

    public UsersController(UsersService usersService, UsersMapper usersMapper) {
        this.usersService = usersService;
        this.usersMapper = usersMapper;
    }

    @GetMapping("{id}")
    @Operation(summary = "Get a user by its id", tags = {"Users", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UsersDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<UsersDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(usersMapper.usersToUsersDTO(usersService.findById(id)));
    }

    @GetMapping("/list")
    @Operation(summary = "Get all user summaries", tags = {"Users", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = UsersMiniDTO.class)))})
    })
    public ResponseEntity<Collection<UsersMiniDTO>> findAll() {
        return ResponseEntity.ok(usersMapper.usersToUsersMiniDTO(usersService.findAll()));
    }

    @GetMapping
    @Operation(summary = "Get all users", tags = {"Users", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = UsersDTO.class)))})
    })
    public ResponseEntity<UsersDTO> findCurrentUser() {
        // TODO Make this 303 See Other
        String id = "9e8ae4c6-7901-4ce3-b562-395fc411e006";
        return ResponseEntity.ok(usersMapper.usersToUsersDTO(usersService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Add a user", tags = {"Users", "Post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content)
    })
    public ResponseEntity<UsersDTO> add() {
        String id = "10";
        String name = "new user";

        Users user = new Users();
        user.setId(id);
        user.setName(name);
        usersService.add(user);
        URI uri = URI.create("api/v1/users/" + user.getId());

        return ResponseEntity.created(uri).body(usersMapper.usersToUsersDTO(usersService.findById(id)));
    }

    @PutMapping("{id}")
    @Operation(summary = "Update a user", tags = {"Users", "Put"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, URI does not match request body", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<UsersDTO> update(@RequestBody UsersPutDTO entity, @PathVariable String id) {
        if (!usersService.exists(id))
            return ResponseEntity.badRequest().build();

        Users users = usersMapper.usersPutDTOToUsers(entity);
        users.setId(id);
        usersService.update(users);
        return ResponseEntity.ok(usersMapper.usersToUsersDTO(usersService.findById(id)));
    }

    private String getNameFromToken(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        System.out.println(payload);
        int nameIndex = payload.indexOf("\"name\":");
        String nameChunk = payload.substring(nameIndex);
        System.out.println(nameChunk);
        String[] nameChunks = nameChunk.split("\"");
        System.out.println(nameChunks[3]);
        return nameChunks[3];
    }
}
