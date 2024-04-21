package co.edu.udea.sitas.controllers.v1;

import co.edu.udea.sitas.domain.dto.FlightDTO;
import co.edu.udea.sitas.domain.model.Flight;
import co.edu.udea.sitas.services.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/v1/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        log.info("Controller initialized");
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<FlightDTO>> findALL(@RequestParam Map<String, String> reqParam){
        List<FlightDTO> foundFlights;
        log.info("Parameters map: {}", reqParam);
        log.info("Search flights");
        foundFlights = flightService.findAll(reqParam).stream().map(FlightDTO::buildFlightDTO).toList();
        return ResponseEntity.ok(foundFlights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDTO> findById(@PathVariable Long id){
        Optional<Flight> optionalFlight = flightService.findById(id);
        if(optionalFlight.isPresent()) {
            FlightDTO flightDTO = FlightDTO.buildFlightDTO(optionalFlight.get());
            return ResponseEntity.ok(flightDTO);
        }
        return ResponseEntity.noContent().build();
    }
}
