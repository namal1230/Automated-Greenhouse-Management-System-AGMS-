package lk.ijse.automation_and_control_service.service;

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

    public void processSensorData(SensorDataDTO data) {

        ZoneDTO zone = zoneClient.getZone(data.getZoneId());

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

    public List<AutomationLog> getLogs() {
        return repository.findAll();
    }
}
