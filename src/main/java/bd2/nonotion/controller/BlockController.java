package bd2.nonotion.controller;

import bd2.nonotion.model.BlockEntity;
import bd2.nonotion.model.request.BlockCreationRequest;
import bd2.nonotion.model.request.BlockUpdateRequest;
import bd2.nonotion.service.BlockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping(value = "/api")
@RestController
public class BlockController {

    private final BlockService blockService;

    @Operation(summary = "Create a new block")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Block created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BlockEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters",
                    content = @Content) })
    @PostMapping(value = "/block")
    public BlockEntity createBlock(@RequestBody @Valid BlockCreationRequest request) {
        return blockService.createBlock(request);
    }

    @Operation(summary = "Get a block")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Block found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BlockEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Block not found",
                    content = @Content) })
    @GetMapping(value = "/block/{id}")
    public BlockEntity getBlock(@PathVariable String id) {
        return blockService.getBlock(id);
    }

    @Operation(summary = "Delete a block")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Block deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Block not found",
                    content = @Content) })
    @DeleteMapping(value = "/block/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBlock(@PathVariable String id) {
        blockService.deleteBlock(id);
    }

    @Operation(summary = "Edit a block")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Block edited",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BlockEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Block not found",
                    content = @Content) })
    @PutMapping(value = "/block")
    public BlockEntity editBlock(@RequestBody @Valid BlockUpdateRequest request) {
        return blockService.editBlock(request);
    }
}
