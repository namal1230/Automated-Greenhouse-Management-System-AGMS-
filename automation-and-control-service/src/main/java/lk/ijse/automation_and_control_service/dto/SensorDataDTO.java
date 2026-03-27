package lk.ijse.automation_and_control_service.dto;

import lombok.Data;

@Data
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
