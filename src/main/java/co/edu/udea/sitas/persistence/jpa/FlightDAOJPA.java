package co.edu.udea.sitas.persistence.jpa;

import co.edu.udea.sitas.domain.model.Flight;
import co.edu.udea.sitas.persistence.FlightDAO;
import co.edu.udea.sitas.repositories.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class FlightDAOJPA implements FlightDAO {

    private static final Logger log = LoggerFactory.getLogger(FlightDAOJPA.class);
    private final FlightRepository flightRepository;

    @Autowired
    public FlightDAOJPA(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> findAll() {
        List<Flight> flights = flightRepository.findAll();
        log.info("flights {}", flights);
        return flightRepository.findAll();
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Flight> save(Flight flight) {
        return Optional.empty();
    }

    @Override
    public Optional<Flight> update(Long id, Flight body) {
        return Optional.empty();
    }

    @Override
    public Optional<Flight> delete(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Flight> searchByDateInterval(LocalDateTime startDate, LocalDateTime endDate) {
        return flightRepository.searchByDateInterval(startDate, endDate);
    }
}
