package seiji.freightage.model;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private float weight;
}
