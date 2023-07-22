package seiji.freightage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import seiji.freightage.controller.DTO.FreightDTO;
import seiji.freightage.controller.request.FreightCreateRequest;
import seiji.freightage.service.FreightService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/freight")
@RestController
public class FreightController {
    private final FreightService freightService;

    //Get
    @Operation(summary = "Get all freights",
            tags = {"Freights"},
            description = "Get a list of all the freight available",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listed freights")
            })
    @GetMapping("/all")
    public ResponseEntity<List<FreightDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(freightService.findAll());

    }

    //Post
    @Operation(summary = "Create a new freight",
            tags = {"Freights"},
            description = "Create a new freight using a vehicle, a product and a distance as JSON",
            responses = {
                    @ApiResponse(responseCode = "200", description = "New freight created")
            })
    @PostMapping("/create")
    public ResponseEntity<Void> save(@RequestBody @Valid FreightCreateRequest freightCreateRequest){
        freightCreateRequest.save(freightService);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    //Delete
    @Operation(summary = "Delete a freight",
            tags = {"Freights"},
            description = "Delete a single freight using the id of the freight in the end of the URL",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Freight deleted")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        freightService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
