package co.edu.udea.sitas.controllers;

import co.edu.udea.sitas.domain.dto.FlightDTO;
import co.edu.udea.sitas.domain.model.Flight;
import co.edu.udea.sitas.domain.model.Scale;
import co.edu.udea.sitas.services.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        log.info("Controller initialized");
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<FlightDTO>> findALL(){
        log.info("Search all flights");
        List<FlightDTO> flightDTOS = flightService.findAll()
                .stream().map(this::buildFlightDTO).toList();
        return ResponseEntity.ok(flightDTOS);
    }

    @GetMapping(params = {"startDate", "endDate"})
    public ResponseEntity<List<FlightDTO>> searchByDate(
            @RequestParam(name = "startDate") String startDate,
            @RequestParam(name = "endDate") String endDate) {
        LocalDateTime parsedStartDate = LocalDateTime.parse(startDate);
        LocalDateTime parsedEndDate = LocalDateTime.parse(endDate);
        log.info("Search flights by dates");
        List<FlightDTO> foundFlights = flightService
                .searchByDateInterval(parsedStartDate, parsedEndDate)
                .stream().map(this::buildFlightDTO).toList();
        return ResponseEntity.ok(foundFlights);
    }

    private FlightDTO buildFlightDTO(Flight flight){
        log.info("convert flight in a FlightDTO");
        int scaleNumber = flight.getScales().size();
        Scale initialScale = flight.getScales().get(0);
        Scale finalScale = flight.getScales().get(scaleNumber - 1);

        return FlightDTO.builder()
                .flightId(flight.getFlightId())
                .flightNumber(flight.getFlightNumber())
                .basePrice(flight.getBasePrice())
                .taxPercent(flight.getTaxPercent())
                .surcharge(flight.getSurcharge())
                .scaleNumber(scaleNumber)
                .originAirport(initialScale.getOriginAirport())
                .destinationAirport(finalScale.getDestinationAirport())
                .departureDate(initialScale.getDepartureDate())
                .arrivalDate(finalScale.getArrivalDate())
                .build();
    }

}
