package co.edu.udea.sitas.services;


import co.edu.udea.sitas.domain.model.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportService {
    List<Airport> findAll();
    Airport save(Airport airport);
    Optional<Airport> findById(Long id);
    Optional<Airport> update(Long id, Airport airport);
    Optional<Airport> delete(Long id);
}
