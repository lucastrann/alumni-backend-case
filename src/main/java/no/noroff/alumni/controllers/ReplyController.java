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
@RequestMapping(path = "api/v1/reply")
public class ReplyController {
    private final ReplyService replyService;
    private final ReplyMapper replyMapper;

    public ReplyController(ReplyService replyService, ReplyMapper replyMapper) {
        this.replyService = replyService;
        this.replyMapper = replyMapper;
    }

    @GetMapping("{id}")
    @Operation(summary = "Get a reply by its id", tags = {"Reply", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReplyDTO.class))),
            @ApiResponse(responseCode = "404", description = "Reply not found", content = @Content)
    })
    public ResponseEntity<ReplyDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(replyMapper.replyToReplyDTO(replyService.findById(id)));
    }

    @GetMapping("/list")
    @Operation(summary = "Get all reply summaries", tags = {"Reply", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ReplyMiniDTO.class)))})
    })
    public ResponseEntity<Collection<ReplyMiniDTO>> findAll() {
        return ResponseEntity.ok(replyMapper.replyToReplyMiniDTO(replyService.findAll()));
    }

    @PostMapping
    @Operation(summary = "Add a reply", tags = {"Reply", "Post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content)
    })
    public ResponseEntity<ReplyDTO> add() {
        String id = "10";
        String content = "New reply";

        Reply reply = new Reply();
        reply.setId(id);
        reply.setContent(content);
        replyService.add(reply);
        URI uri = URI.create("api/v1/reply/" + reply.getId());

        return ResponseEntity.created(uri).body(replyMapper.replyToReplyDTO(replyService.findById(id)));
    }

    @PutMapping("{id}")
    @Operation(summary = "Update a reply", tags = {"Reply", "Put"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reply updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, URI does not match request body", content = @Content),
            @ApiResponse(responseCode = "404", description = "Reply not found", content = @Content)
    })
    public ResponseEntity<ReplyDTO> update(@RequestBody ReplyPutDTO entity, @PathVariable String id) {
        if (!replyService.exists(id))
            return ResponseEntity.badRequest().build();

        Reply reply = replyMapper.replyPutDTOToReply(entity);
        reply.setId(id);
        replyService.update(reply);
        return ResponseEntity.ok(replyMapper.replyToReplyDTO(replyService.findById(id)));
    }
}
