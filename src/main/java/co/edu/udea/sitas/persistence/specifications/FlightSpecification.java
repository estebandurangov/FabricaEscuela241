package co.edu.udea.sitas.persistence.specifications;

import co.edu.udea.sitas.domain.model.Flight;
import co.edu.udea.sitas.domain.model.Scale;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class FlightSpecification {

    /**
     * Builds a specification that searches flights with a given flight ID.
     *
     * @param flightId the flight ID to search flights
     * @return the flight specification
     */
    public static Specification<Flight> withFlightId(Long flightId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("flightId"), flightId);
    }

    /**
     * This builds a specification to search flights that their departure date is after a given date,
     *
     * @param date limit date to search flights
     * @return the built specification query
     */
    public static Specification<Flight> flightsDepartureAfter(LocalDateTime date) {
        return (Root<Flight> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (date != null) {
                Subquery<LocalDateTime> earliestDepartureSubquery = query.subquery(LocalDateTime.class);
                Root<Scale> scale = earliestDepartureSubquery.from(Scale.class);
                Expression<LocalDateTime> earliestDeparture = scaleFetchFirst(scale, builder, root, earliestDepartureSubquery, true);
                return builder.greaterThanOrEqualTo(earliestDeparture, date);
            } else {
                return builder.isTrue(builder.literal(true));
            }
        };
    }

    /**
     * This builds a specification to search flights which their arrival date is before a given date
     * @param date limit date to search flights
     * @return the built specification query
     */

    private static final String FLIGHT = "flight";

    public static Specification<Flight> flightsDepartureBefore(LocalDateTime date) {
        return (Root<Flight> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Subquery<LocalDateTime> latestDepartureSubquery = query.subquery(LocalDateTime.class);
            Root<Scale> scale = latestDepartureSubquery.from(Scale.class);
            latestDepartureSubquery.select(scale.get("arrivalDate"))
                    .where(builder.equal(scale.get(FLIGHT), root));

            Expression<LocalDateTime> latestDeparture = scaleFetchFirst(scale, builder, root, latestDepartureSubquery, false);
            return builder.lessThanOrEqualTo(latestDeparture, date);
        };
    }

    /**
     * Builds a specification that search flights with a given flight number
     * @param flightNumber the param to search flights
     * @return the flight specification
     */
    public static Specification<Flight> withFlightNumber(String flightNumber) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("flightNumber"), flightNumber);
    }

    /**
     * Returns a Specification to filter flights with the specified origin city.
     *
     * @param city The origin city to filter flights by.
     * @return A Specification for filtering flights by origin city.
     */
    public static Specification<Flight> withOriginCity(String city) {
        return (Root<Flight> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.equal(root.join("scales").join("originAirport").get("city"), city);
    }

    /**
     * Returns a Specification to filter flights with the specified destination city.
     *
     * @param city The destination city to filter flights by.
     * @return A Specification for filtering flights by destination city.
     */
    public static Specification<Flight> withDestinationCity(String city) {
        return (Root<Flight> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Subquery<String> destinationCitySubquery = query.subquery(String.class);
            Root<Scale> scale = destinationCitySubquery.from(Scale.class);
            destinationCitySubquery.select(scale.join("destinationAirport").get("city"))
                    .where(builder.equal(scale.get(FLIGHT), root));

            return builder.equal(
                    destinationCitySubquery.getSelection(),
                    city
            );
        };
    }

    /**
     * Returns a Specification to filter flights with the specified origin airport code.
     *
     * @param airportCode The origin airport code to filter flights by.
     * @return A Specification for filtering flights by origin airport code.
     */
    public static Specification<Flight> withOriginAirport(String airportCode) {
        return (Root<Flight> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.equal(root.join("scales").join("originAirport").get("airportCode"), airportCode);
    }

    /**
     * Returns a Specification to filter flights with the specified destination airport code.
     *
     * @param airportCode The destination airport code to filter flights by.
     * @return A Specification for filtering flights by destination airport code.
     */
    public static Specification<Flight> withDestinationAirport(String airportCode) {
        return (Root<Flight> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Subquery<String> destinationCitySubquery = query.subquery(String.class);
            Root<Scale> scale = destinationCitySubquery.from(Scale.class);
            destinationCitySubquery.select(scale.join("destinationAirport").get("airportCode"))
                    .where(builder.equal(scale.get(FLIGHT), root));

            return builder.equal(
                    destinationCitySubquery.getSelection(),
                    airportCode
            );
        };
    }

    /**
     * Builds a specification that search flights with a min price limit
     * @param minPrice the param to search flights
     * @return the flight specification
     */
    public static Specification<Flight> withBasePriceGreaterThan(Float minPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("basePrice"), minPrice);
    }

    /**
     * Builds a specification that search flights with a max price limit
     * @param maxPrice the param to search flights
     * @return the flight specification
     */
    public static Specification<Flight> withBasePriceLessThan(Float maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("basePrice"), maxPrice);
    }


    private static Expression<LocalDateTime> scaleFetchFirst(Root<Scale> scale, CriteriaBuilder builder,
                                                             Root<Flight> root,
                                                             Subquery<LocalDateTime> subquery,
                                                             boolean asc) {
        subquery.select(builder.function(asc ? "min" : "max", LocalDateTime.class, scale.get("departureDate")))
                .where(builder.equal(scale.get(FLIGHT), root));
        return subquery.getSelection();
    }
}