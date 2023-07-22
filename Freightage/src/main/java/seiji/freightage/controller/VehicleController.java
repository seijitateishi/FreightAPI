package seiji.freightage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seiji.freightage.controller.DTO.VehicleDTO;
import seiji.freightage.model.Vehicle;
import seiji.freightage.service.VehicleService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/vehicle")
@RestController
public class VehicleController {
    private final VehicleService vehicleService;


    //Get
    @Operation(summary = "Get all vehicles",
            tags = {"Vehicles"},
            description = "Get a list of all the vehicles available",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listed vehicles")
            })
    @GetMapping("/all")
    public List<VehicleDTO> findAll(){
        return vehicleService.findAll();
    }

    //Post
    @Operation(summary = "Create a new vehicle",
            tags = {"Vehicles"},
            description = "Create a new vehicle using a 'name' and a 'weight' as JSON",
            responses = {
                    @ApiResponse(responseCode = "200", description = "New freight created")
            })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid Vehicle vehicle){
        vehicleService.save(vehicle);
        return ResponseEntity.status(200).build();
    }

    //Delete
    @Operation(summary = "Delete a vehicle",
            tags = {"Vehicles"},
            description = "Delete a single vehicle using the id of the vehicle at the end of the URL",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Freight deleted")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehicleService.deleteById(id);
        return ResponseEntity.status(200).build();
    }
}
