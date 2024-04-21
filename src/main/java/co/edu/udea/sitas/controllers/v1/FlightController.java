package co.edu.udea.sitas.controllers.v1;

import co.edu.udea.sitas.domain.dto.FlightDTO;
import co.edu.udea.sitas.domain.model.Flight;
import co.edu.udea.sitas.services.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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
        log.info("Search flights by dates");
        foundFlights = flightService.findAll(reqParam).stream().map(FlightDTO::buildFlightDTO).toList();
        return ResponseEntity.ok(foundFlights);
    }


    private Flight buildFlight(FlightDTO flightDTO) {
        return new Flight();
    }

}
