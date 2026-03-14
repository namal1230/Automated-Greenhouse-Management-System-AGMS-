package lk.ijse.zone_management_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Zone {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double minTemp;
    private double maxTemp;
    private String deviceId;
}
