package seiji.freightage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import seiji.freightage.controller.DTO.FreightDTO;
import seiji.freightage.enums.Status;
import seiji.freightage.model.Freight;
import seiji.freightage.model.User;
import seiji.freightage.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FreightService freightService;

    public List<FreightDTO> freightsDone(){
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();//o proprio usuario
        Optional<User> user = userRepository.findById(userId);
        return user.get().getFreightList().stream().filter(freight -> freight.getStatus() == Status.FINISHED).map(FreightDTO::new).toList();
    }

    public void requestFreight(Long freightId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Freight freight = freightService.findById(freightId);
        if (freight.getStatus() != Status.NOT_ACCEPTED){
            return;
        }
        freight.setStatus(Status.ACCEPTED);
        freight.setUser(user);
        user.getFreightList().add(freight);
        freightService.save(freight);
        userRepository.save(user);
    }

    public void freightCancel(Long freightId){
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Optional<User> user = userRepository.findById(userId);
        Optional<Freight> freight = user
                .get()
                .getFreightList()
                .stream()
                .filter(freight1 -> Objects.equals(freight1.getId(), freightId))
                .findFirst();
        if (freight.isEmpty()){
            return;
        }
        freight.orElseThrow().setStatus(Status.NOT_ACCEPTED);
        freightService.save(freight.orElseThrow());
    }
}
