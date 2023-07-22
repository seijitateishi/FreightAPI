package seiji.freightage.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import seiji.freightage.enums.Status;
import seiji.freightage.model.Freight;
import seiji.freightage.model.Product;
import seiji.freightage.model.Vehicle;
import seiji.freightage.service.FreightService;

@Data
public class FreightCreateRequest {
    @NotNull
    private float distance;

    @NotNull
    private Product product;

    @NotNull
    private Vehicle vehicle;

    public void save(FreightService freightService) {
        if (this.distance <= 0) {
            throw new IllegalArgumentException("Distance must be higher than 0(zero)!");
        }
        freightService.save(Freight.builder().
                distance(distance).
                product(product).
                vehicle(vehicle).
                status(Status.NOT_ACCEPTED).
                build());
    }

}
