import co.edu.udea.sitas.domain.dto.FlightDTO;
import co.edu.udea.sitas.controllers.v1.FlightController;
import co.edu.udea.sitas.domain.model.Flight;
import co.edu.udea.sitas.services.FlightService;
import static org.mockito.Mockito.*;

import co.edu.udea.sitas.services.ScaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FlightDAOJPATest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() {
        // Arrange
        FlightService flightServiceMock = mock(FlightService.class);
        ScaleService scaleServiceMock = mock(ScaleService.class);
        FlightController flightController = new FlightController(flightServiceMock, scaleServiceMock);
        Map<String, String> reqParam = new HashMap<>();
        reqParam.put("param1", "value1");
        reqParam.put("param2", "value2");
        List<Flight> mockFlights = List.of(new Flight(), new Flight()); // Mocked flights
        when(flightServiceMock.findAll(reqParam)).thenReturn(mockFlights);

        // Act
        ResponseEntity<List<FlightDTO>> responseEntity = flightController.findALL(reqParam);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}