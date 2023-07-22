package seiji.freightage.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private float weight;

}
