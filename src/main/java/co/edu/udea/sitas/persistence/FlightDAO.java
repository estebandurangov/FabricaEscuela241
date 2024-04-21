package co.edu.udea.sitas.persistence;

import co.edu.udea.sitas.domain.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightDAO {
    List<Flight> findAll();
    Optional<Flight> findById(Long id);
    Optional<Flight>  save(Flight flight);
    Optional<Flight>   update(Long id, Flight body);
    Optional<Flight>   delete(Long id);
    List<Flight> searchByDateInterval(LocalDateTime startDate, LocalDateTime endDate);
}
