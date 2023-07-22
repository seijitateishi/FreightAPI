package seiji.freightage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seiji.freightage.controller.DTO.ProductDTO;
import seiji.freightage.model.Product;
import seiji.freightage.service.ProductService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/product")
@RestController
public class ProductController {
    private final ProductService productService;

    //Get
    @Operation(summary = "Get all products",
            tags = {"Products"},
            description = "Get a list of all the products available",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listed products")
            })
    @GetMapping("/all")
    public List<ProductDTO> findAll(){
        return productService.findAll();
    }

    //Post
    @Operation(summary = "Create a new product",
            tags = {"Products"},
            description = "Create a new product using a 'name' and a 'weight' as JSON",
            responses = {
                    @ApiResponse(responseCode = "200", description = "New product created")
            })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid Product product){
        productService.save(product);
        return ResponseEntity.status(200).build();
    }

    //Put
    @Operation(summary = "Change a weight of a product",
            tags = {"Products"},
            description = "Change the weight of a product using the id of the product and the new weight in the end of the URL",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product updated")
            })
    @PutMapping("/{productId}/{weight}")
    public ResponseEntity<Void> updateWeight(@PathVariable Long productId, @PathVariable float weight){
        productService.updateWeight(productId, weight);
        return ResponseEntity.status(200).build();
    }




    //Delete
    @Operation(summary = "Delete a product",
            tags = {"Products"},
            description = "Delete a single product using the id of the product in the end of the URL",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product deleted")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.status(200).build();
    }
}
