package co.edu.udea.sitas.services;

import co.edu.udea.sitas.domain.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    List<Flight> findAll();
    Flight findById(Long id);
    Flight save(Flight flight);
    Flight update(Long id, Flight body);
    Flight delete(Long id);
    List<Flight> searchByDateInterval(LocalDateTime startDate, LocalDateTime endDate);
}
