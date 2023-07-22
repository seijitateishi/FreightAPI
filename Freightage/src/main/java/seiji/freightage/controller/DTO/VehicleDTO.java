package seiji.freightage.controller.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import seiji.freightage.model.Vehicle;

@NoArgsConstructor
@Getter
public class VehicleDTO {
    private String name;

    private float weight;

    public VehicleDTO(Vehicle vehicle){
        this.name = vehicle.getName();
        this.weight = vehicle.getWeight();
    }
}
