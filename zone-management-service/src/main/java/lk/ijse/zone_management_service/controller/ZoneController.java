package lk.ijse.zone_management_service.controller;

import lk.ijse.zone_management_service.entity.Zone;
import lk.ijse.zone_management_service.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {
    @Autowired
    ZoneService service;

    @PostMapping
    public Zone create(@RequestBody Zone zone){
        return service.create(zone);
    }

    @GetMapping("/{id}")
    public Zone get(@PathVariable Long id){
        return service.get(id);
    }
}
