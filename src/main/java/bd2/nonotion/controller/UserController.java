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
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping(value = "/api")
@RestController
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters",
                    content = @Content) })
    @GetMapping(value = "/user/{userId}")
    public UserEntity getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters",
                    content = @Content) })
    @PostMapping(value = "/user")
    public UserEntity createUser(@RequestBody @Valid UserCreationRequest request) {
        return userService.createUser(request);
    }

    @Operation(summary = "Edit a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Edited",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters",
                    content = @Content) })
    @PutMapping(value = "/user/{userId}")
    public UserEntity updateUser(@RequestBody @Valid UserCreationRequest request,
                                 @PathVariable String userId) {
        return userService.updateUser(request, userId);
    }

    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters",
                    content = @Content) })
    @DeleteMapping(value = "/user/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
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
