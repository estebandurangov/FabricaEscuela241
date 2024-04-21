package co.edu.udea.sitas.domain.dto;

import co.edu.udea.sitas.domain.model.Airport;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightDTO {
    // Attributes
    private Long flightId;
    private String flightNumber;
    private float basePrice;
    private float taxPercent;
    private float surcharge;
    private Integer scaleNumber;
    private Airport originAirport;
    private Airport destinationAirport;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
}
