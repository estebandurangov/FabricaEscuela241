package co.edu.udea.sitas.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Flight")
public class Flight {
    @Id
    @Column(name = "flight_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long flightId;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "base_price")
    private float basePrice;

    @Column(name = "tax_percent")
    private float taxPercent;

    @Column(name = "surcharge")
    private float surcharge;

    @JsonIgnore
    @OneToMany(mappedBy = "flight_id", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Scale> scales;
}
