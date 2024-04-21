package co.edu.udea.sitas.services;

import co.edu.udea.sitas.domain.model.AirplaneModel;

import java.util.List;
import java.util.Optional;

public interface AirplaneModelService {
    List<AirplaneModel> findAll();
    AirplaneModel save(AirplaneModel airplaneModel);
    Optional<AirplaneModel> findById(Long id);
    Optional<AirplaneModel> update(Long id, AirplaneModel airplaneModel);
    Optional<AirplaneModel> delete(Long id);
}
