package seiji.freightage.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.ComponentScan;
import seiji.freightage.controller.FreightController;
import seiji.freightage.enums.Status;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Freight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private float distance;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
