package seiji.freightage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seiji.freightage.controller.DTO.FreightDTO;
import seiji.freightage.enums.Status;
import seiji.freightage.model.Freight;
import seiji.freightage.repository.FreightRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FreightService {
    private final FreightRepository freightRepository;

    public List<FreightDTO> findAll(){
        return freightRepository.findAll().stream().map(FreightDTO::new).toList();
    }

    public List<FreightDTO> findAllNotAccepted(){
        return freightRepository.findAll()
                .stream()
                .filter(freight -> freight.getStatus() == Status.NOT_ACCEPTED)
                .map(FreightDTO::new)
                .toList();
    }

    public FreightDTO findByIdDTO(Long id){
        return new FreightDTO(Objects.requireNonNull(Objects.requireNonNull(freightRepository.findById(id)).orElse(null)));
    }

    public Freight findById(Long id){
        return freightRepository.findById(id).orElse(null);
    }

    public void save(Freight freight){
        freightRepository.save(freight);
    }

    public void delete(Long id){
        freightRepository.deleteById(id);
    }


    public void updateStatus(Long id, Status status){
        Optional<Freight> freight = freightRepository.findById(id);
        if (freight.isEmpty()){
            return;
        }
        freight.orElseThrow().setStatus(status);
        freightRepository.save(freight.orElseThrow());
    }

    public void updateStatus(Long id){
        Optional<Freight> freight = freightRepository.findById(id);
        if (freight.isEmpty()){
            return;
        }
        if (freight.get().getStatus() == Status.ACCEPTED){
            freight.orElseThrow().setStatus(Status.IN_PROGRESS);
        }else if (freight.get().getStatus() == Status.IN_PROGRESS){
            freight.orElseThrow().setStatus(Status.FINISHED);
        }
        freightRepository.save(freight.orElseThrow());
    }



}
