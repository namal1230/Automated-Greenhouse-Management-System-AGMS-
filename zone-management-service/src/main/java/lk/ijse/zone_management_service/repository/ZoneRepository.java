package lk.ijse.zone_management_service.repository;

import lk.ijse.zone_management_service.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone,Long> {
}
