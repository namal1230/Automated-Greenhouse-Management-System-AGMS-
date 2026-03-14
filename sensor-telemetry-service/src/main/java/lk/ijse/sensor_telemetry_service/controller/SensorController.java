package lk.ijse.sensor_telemetry_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {
    private String latestDate;

    @GetMapping("/latest")
    public String latest(){
        return latestDate;
    }
}
