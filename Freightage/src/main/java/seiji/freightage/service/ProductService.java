package seiji.freightage.service;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import seiji.freightage.controller.DTO.FreightDTO;
import seiji.freightage.controller.DTO.ProductDTO;
import seiji.freightage.model.Product;
import seiji.freightage.repository.ProductRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDTO> findAll(){
        return productRepository.findAll().stream().map(ProductDTO::new).toList();
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public void save(Product product){
        productRepository.save(product);
        ResponseEntity.status(200).build();
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
        ResponseEntity.status(200).build();
    }

    public void updateWeight(Long id, float weight){
        Product product = productRepository.findById(id).orElse(null);
        if (product == null){
            ResponseEntity.status(404).build();
            return;
        }
        product.setWeight(weight);
        productRepository.save(product);
        ResponseEntity.status(200).build();
    }
}
