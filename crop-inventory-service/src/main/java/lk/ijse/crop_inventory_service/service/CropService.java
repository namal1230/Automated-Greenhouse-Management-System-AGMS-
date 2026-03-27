package lk.ijse.crop_inventory_service.service;

import lk.ijse.crop_inventory_service.constatns.CropStage;
import lk.ijse.crop_inventory_service.dto.CropDTO;
import lk.ijse.crop_inventory_service.entity.Crop;
import lk.ijse.crop_inventory_service.repository.CropRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CropService {

    private final CropRepository repository;

    public Crop createCrop(CropDTO dto) {
        Crop crop = new Crop();
        crop.setName(dto.getName());
        crop.setStage(dto.getStage());
        crop.setQuantity(dto.getQuantity());
        return repository.save(crop);
    }

    public List<Crop> getAllCrops() {
        return repository.findAll();
    }

    public Optional<Crop> getCropById(String id) {
        return repository.findById(id);
    }

    public Crop updateCropStage(String id, CropStage newStage) {
        Crop crop = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop not found with id: " + id));
        crop.setStage(newStage);
        return repository.save(crop);
    }

    public void deleteCrop(String id) {
        repository.deleteById(id);
    }
}
