package lk.ijse.automation_and_control_service.controller;

import lk.ijse.automation_and_control_service.dto.SensorDataDTO;
import lk.ijse.automation_and_control_service.entity.AutomationLog;
import lk.ijse.automation_and_control_service.service.AutomationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/automation")
@RequiredArgsConstructor
public class AutomationController {

    private final AutomationService service;

    @PostMapping("/process")
    public ResponseEntity<String> process(
            @RequestBody SensorDataDTO data) {

        service.processSensorData(data);
        return ResponseEntity.ok("Processed");
    }

    @GetMapping("/logs")
    public List<AutomationLog> logs() {
        return service.getLogs();
    }
}
