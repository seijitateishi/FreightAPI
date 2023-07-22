package seiji.freightage.controller.DTO;

import lombok.Getter;
import seiji.freightage.model.Product;

@Getter
public class ProductDTO {
    private String name;

    private float weight;

    public ProductDTO(Product product){
        this.name = product.getName();
        this.weight = product.getWeight();
    }
}
