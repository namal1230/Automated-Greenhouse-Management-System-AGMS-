package lk.ijse.sensor_telemetry_service.service;

import lk.ijse.sensor_telemetry_service.dto.SensorDataDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class TelemetryFetcher {

    private final RestTemplate restTemplate;

    public TelemetryFetcher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 10000)
    public void fetchData() {

        // Create sensor reading
        SensorDataDTO.Value value = new SensorDataDTO.Value();
        value.setTemperature(25 + Math.random() * 10); // 25-35°C
        value.setHumidity(50 + Math.random() * 20);    // 50-70%

        SensorDataDTO reading = new SensorDataDTO();
        reading.setDeviceId("test-device");
        reading.setZoneId("zone-1"); // must exist in Zone Service
        reading.setValue(value);

        forwardToAutomation(reading);
    }

    private void forwardToAutomation(SensorDataDTO data) {
        try {
            restTemplate.postForObject(
                    "http://localhost:8083/api/automation/process",
                    data,
                    String.class
            );
        } catch (Exception e) {
            System.err.println("Failed to forward data to Automation Service: " + e.getMessage());
        }
    }
}
