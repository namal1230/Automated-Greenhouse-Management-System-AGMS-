package lk.ijse.sensor_telemetry_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorDataDTO {
    private String deviceId;
    private String zoneId;
    private Value value;

    @Data
    public static class Value {
        private double temperature;
        private double humidity;
    }
}
