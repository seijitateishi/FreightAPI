package seiji.freightage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import seiji.freightage.controller.DTO.FreightDTO;
import seiji.freightage.enums.Status;
import seiji.freightage.service.FreightService;
import seiji.freightage.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
@PreAuthorize("hasAnyAuthority('ADMIN','DRIVER')")
public class UserController {
    private final UserService userService;
    private final FreightService freightService;

    @Operation(summary = "Freights done by a user",
            tags = {"User"},
            description = "Get a list of all the freights done by a user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "New user created")
            })
    @GetMapping("/freight/done")
    @PreAuthorize("hasAuthority('DRIVER')")
    public ResponseEntity<List<FreightDTO>> freightsDone(){
        return ResponseEntity.ok(userService.freightsDone());
    }

    @Operation(summary = "Update the status of a freight",
            tags = {"User"},
            description = "Update the status of a freight using the id of the freight and the new status in the end of the URL",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Freight updated")
            })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/freight/update/{freightId}/{status}")
    public ResponseEntity<Void> updateStatus(@PathVariable Long freightId, @PathVariable Status status){
        freightService.updateStatus(freightId, status);
        return ResponseEntity.status(200).build();
    }

    @Operation(summary = "Update the status of a freight",
            tags = {"User"},
            description = "Update the status of a freight to the next step of the freight using the id of the freight in the end of the URL",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Freight updated")
            })
    @PutMapping("/freight/update/{freightId}")
    @PreAuthorize("hasAuthority('DRIVER')")
    public ResponseEntity<Void>updateStatus(@PathVariable Long freightId){
        freightService.updateStatus(freightId);
        return ResponseEntity.status(200).build();
    }


    @Operation(summary = "Cancel a freight",
            tags = {"User"},
            description = "Cancel a freight using the id of the freight in the end of the URL",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Freight updated")
            })
    @PutMapping("/freight/cancel/{freightId}")
    @PreAuthorize("hasAuthority('DRIVER')")
    public ResponseEntity<Void> freightCancel(@PathVariable Long freightId){
        userService.freightCancel(freightId);
        return ResponseEntity.status(200).build();
    }

    @Operation(summary = "Request a freight",
            tags = {"User"},
            description = "Request a freight using the id of the freight in the end of the URL",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Freight updated")
            })
    @PutMapping("/freight/{freightId}")
    @PreAuthorize("hasAuthority('DRIVER')")
    public ResponseEntity<Void> requestFreight(@PathVariable Long freightId){
        userService.requestFreight(freightId);
        return ResponseEntity.status(200).build();
    }
}
