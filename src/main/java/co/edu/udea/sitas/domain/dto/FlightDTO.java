package co.edu.udea.sitas.domain.dto;

import co.edu.udea.sitas.domain.model.Airport;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightDTO {
    // Attributes
    private Long flightId;
    private String airplaneModel;
    private String flightNumber;
    private float basePrice;
    private float taxPercent;
    private float surcharge;
    private Airport originAirport;
    private Airport destinationAirport;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
}
