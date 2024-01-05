package bd2.nonotion.controller;

import bd2.nonotion.model.BlockEntity;
import bd2.nonotion.model.UserEntity;
import bd2.nonotion.model.request.UserCreationRequest;
import bd2.nonotion.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping(value = "/api")
@RestController
public class UserController {

    private final UserService userService;

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters",
                    content = @Content) })
    @PostMapping(value = "/user")
    public UserEntity createUser(@RequestBody UserCreationRequest request) {
        return userService.createUser(request);
    }

    @Operation(summary = "Get all blocks from user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blocks found",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BlockEntity.class))) }),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @GetMapping(value = "/user/{id}/blocks")
    public List<BlockEntity> getBlocks(@PathVariable String id) {
        return userService.getBlocks(id);
    }
}
