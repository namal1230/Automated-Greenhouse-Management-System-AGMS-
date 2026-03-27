package lk.ijse.automation_and_control_service.service;

import lk.ijse.automation_and_control_service.dto.SensorDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "AUTOMATION-SERVICE")
public interface AutomationClient {

    @PostMapping("/api/automation/process")
    void sendData(SensorDataDTO data);
}
