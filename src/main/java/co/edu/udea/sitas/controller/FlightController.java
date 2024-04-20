package co.edu.udea.sitas.controller;

import co.edu.udea.sitas.domain.dto.FlightDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @GetMapping
    public ResponseEntity<List<FlightDTO>> findALL(){
        return ResponseEntity.ok(Collections.singletonList(FlightDTO.builder().flightId(1L).build()));
    }

}
