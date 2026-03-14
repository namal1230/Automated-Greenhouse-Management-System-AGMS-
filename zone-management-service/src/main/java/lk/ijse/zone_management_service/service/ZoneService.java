package lk.ijse.zone_management_service.service;

import lk.ijse.zone_management_service.entity.Zone;
import lk.ijse.zone_management_service.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZoneService {
    @Autowired
    ZoneRepository repo;

    public Zone create(Zone zone){
        if (zone.getMinTemp() >= zone.getMaxTemp()){
            throw new RuntimeException("Invalid temperature range");
        }
        return repo.save(zone);
    }

    public Zone get(Long id){
        return repo.findById(id).get();
    }
}
