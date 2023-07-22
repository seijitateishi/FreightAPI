package seiji.freightage.controller.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seiji.freightage.enums.Status;
import seiji.freightage.model.Freight;
import seiji.freightage.model.Product;
import seiji.freightage.model.Vehicle;

@Setter
@NoArgsConstructor
@Getter
public class FreightDTO {
    private Long id;

    private float distance;

    private Product product;

    private Vehicle vehicle;

    private float tax;

    private float freightPrice;

    private Status status;

    private float driverPayment;


    public FreightDTO(Freight freight){
        float fullPrice = freight.getVehicle().getWeight() * freight.getProduct().getWeight() * freight.getDistance();
        this.id = freight.getId();
        this.distance = freight.getDistance();
        if (freight.getDistance() < 100.0){
            this.tax = (float) (fullPrice * 0.05);
        } else if (freight.getDistance() < 200.0){
            this.tax = (float) (fullPrice * 0.07);
        } else if(freight.getDistance() < 500.0){
            this.tax = (float) (fullPrice * 0.09);
        }else{
            this.tax = (float) (fullPrice * 0.1);
        }

        this.product = freight.getProduct();
        this.vehicle = freight.getVehicle();
        this.status = freight.getStatus();
        this.freightPrice = fullPrice;
        this.driverPayment = fullPrice - this.tax;
    }
}
