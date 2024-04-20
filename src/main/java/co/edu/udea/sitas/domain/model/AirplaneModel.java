package co.edu.udea.sitas.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "AirplaneModel")
public class AirplaneModel {
    @Id
    @Column(name = "airplane_model")
    private String airplaneModel;

    @Column(name = "family")
    private String family;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "cargo_capacity")
    private float cargoCapacity;

    @Column(name = "volume_capacity")
    private float volumeCapacity;
}
