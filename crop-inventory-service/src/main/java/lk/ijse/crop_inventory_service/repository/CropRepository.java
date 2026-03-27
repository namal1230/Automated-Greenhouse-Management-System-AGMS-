package lk.ijse.crop_inventory_service.repository;

import lk.ijse.crop_inventory_service.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, String> {
}
