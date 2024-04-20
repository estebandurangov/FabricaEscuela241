package co.edu.udea.sitas.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Scale")
public class Scale {
    @Id
    @Column(name = "scale_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long scaleId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "airplane_model", nullable = false)
    private AirplaneModel airplaneModel;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "origin_airport", nullable = false)
    private Airport originAirport;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "destination_airport", nullable = false)
    private Airport destinationAirport;

    @Column(name = "departure_date")
    private LocalDateTime departureDate;

    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;
}
