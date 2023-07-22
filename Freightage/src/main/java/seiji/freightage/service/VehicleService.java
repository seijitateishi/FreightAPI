package seiji.freightage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import seiji.freightage.controller.DTO.VehicleDTO;
import seiji.freightage.model.Vehicle;
import seiji.freightage.repository.VehicleRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public List<VehicleDTO> findAll(){
        return vehicleRepository.findAll().stream().map(VehicleDTO::new).toList();
    }

    public Vehicle findById(Long id){
        return vehicleRepository.findById(id).orElse(null);
    }

    public void save(Vehicle vehicle){
        vehicleRepository.save(vehicle);
        ResponseEntity.status(200).build();
    }

    public void deleteById(Long id){
        vehicleRepository.deleteById(id);
        ResponseEntity.status(200).build();
    }

    public ResponseEntity<Void> updateWeight(Long id, float weight){
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
        if (vehicle == null){
            return ResponseEntity.status(404).build();
        }
        vehicle.setWeight(weight);
        vehicleRepository.save(vehicle);
        return ResponseEntity.status(200).build();
    }
}
