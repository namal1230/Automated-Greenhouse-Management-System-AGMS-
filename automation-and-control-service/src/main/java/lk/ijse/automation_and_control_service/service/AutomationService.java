package lk.ijse.automation_and_control_service.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lk.ijse.automation_and_control_service.dto.SensorDataDTO;
import lk.ijse.automation_and_control_service.dto.ZoneDTO;
import lk.ijse.automation_and_control_service.entity.AutomationLog;
import lk.ijse.automation_and_control_service.repository.AutomationLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutomationService {

    private final ZoneClient zoneClient;
    private final AutomationLogRepository repository;

    @Retry(name = "zone-management-service")
    @CircuitBreaker(name = "zone-management-service", fallbackMethod = "zoneFallback")
    public void processSensorData(SensorDataDTO data) {

        if (data == null || data.getValue() == null) {
            throw new IllegalArgumentException("Sensor data or value cannot be null");
        }

        Long zoneId = parseZoneId(data.getZoneId());
        ZoneDTO zone = zoneClient.getZone(zoneId);
        if (zone == null) {
            throw new IllegalArgumentException("Zone not found for id: " + data.getZoneId());
        }

        double temp = data.getValue().getTemperature();
        double humidity = data.getValue().getHumidity();

        String action = "NO_ACTION";

        if (temp > zone.getMaxTemp()) {
            action = "TURN_FAN_ON";
        } else if (temp < zone.getMinTemp()) {
            action = "TURN_HEATER_ON";
        }

        AutomationLog log = new AutomationLog(
                null,
                data.getZoneId(),
                temp,
                humidity,
                action,
                LocalDateTime.now()
        );

        repository.save(log);
    }

    public void zoneFallback(SensorDataDTO data, Exception ex) {
        System.out.println("Fallback triggered!");

        AutomationLog log = new AutomationLog(
                null,
                data.getZoneId(),
                data.getValue().getTemperature(),
                data.getValue().getHumidity(),
                "SERVICE_DOWN",
                LocalDateTime.now()
        );

        repository.save(log);
    }

    public List<AutomationLog> getLogs() {
        return repository.findAll();
    }

    private Long parseZoneId(String zoneId) {
        if (zoneId == null || zoneId.isBlank()) {
            throw new IllegalArgumentException("Zone id cannot be null or blank");
        }

        String normalized = zoneId.trim();
        if (normalized.startsWith("zone-")) {
            normalized = normalized.substring("zone-".length());
        }

        try {
            return Long.parseLong(normalized);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid zone id format: " + zoneId, ex);
        }
    }
}
