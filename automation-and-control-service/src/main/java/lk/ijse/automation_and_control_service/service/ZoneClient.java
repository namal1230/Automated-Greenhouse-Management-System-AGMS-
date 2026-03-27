package lk.ijse.automation_and_control_service.service;

import lk.ijse.automation_and_control_service.dto.ZoneDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "zone-management-service")
public interface ZoneClient {

    @GetMapping("/api/zones/{id}")
    ZoneDTO getZone(@PathVariable Long id);
}
