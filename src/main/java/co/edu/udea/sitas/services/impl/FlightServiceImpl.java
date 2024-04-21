package co.edu.udea.sitas.services.impl;

import co.edu.udea.sitas.domain.model.Flight;
import co.edu.udea.sitas.persistence.FlightDAO;
import co.edu.udea.sitas.services.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightDAO flightDAO;

    @Autowired
    public FlightServiceImpl(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    @Override
    public List<Flight> findAll() {
        return flightDAO.findAll();
    }

    @Override
    public Flight findById(Long id) {
        return null;
    }

    @Override
    public Flight save(Flight flight) {
        return null;
    }

    @Override
    public Flight update(Long id, Flight body) {
        return null;
    }

    @Override
    public Flight delete(Long id) {
        return null;
    }

    @Override
    public List<Flight> searchByDateInterval(LocalDateTime startDate, LocalDateTime endDate) {
        return flightDAO.searchByDateInterval(startDate, endDate);
    }
}
