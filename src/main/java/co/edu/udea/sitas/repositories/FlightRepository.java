package co.edu.udea.sitas.repositories;

import co.edu.udea.sitas.domain.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT DISTINCT f FROM Flight f " +
            "JOIN FETCH f.scales s " +
            "WHERE s.departureDate = (SELECT MIN(s1.departureDate) FROM Scale s1 WHERE s1.flight = f) " +
            "AND s.arrivalDate = (SELECT MAX(s2.arrivalDate) FROM Scale s2 WHERE s2.flight = f) " +
            "AND s.departureDate BETWEEN :startDate AND :endDate " +
            "AND s.arrivalDate BETWEEN :startDate AND :endDate")
    List<Flight> searchByDateInterval(@Param("startDate") LocalDateTime startDate,
                                      @Param("endDate") LocalDateTime endDate);
 }
