package lk.ijse.crop_inventory_service.controller;

import lk.ijse.crop_inventory_service.constatns.CropStage;
import lk.ijse.crop_inventory_service.dto.CropDTO;
import lk.ijse.crop_inventory_service.entity.Crop;
import lk.ijse.crop_inventory_service.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crops")
@RequiredArgsConstructor
public class CropController {

    private final CropService service;

    @PostMapping
    public ResponseEntity<Crop> createCrop(@RequestBody CropDTO dto) {
        return ResponseEntity.ok(service.createCrop(dto));
    }

    @GetMapping
    public ResponseEntity<List<Crop>> getAllCrops() {
        return ResponseEntity.ok(service.getAllCrops());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Crop> getCrop(@PathVariable String id) {
        return service.getCropById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Crop> updateCropStage(
            @PathVariable String id,
            @RequestParam CropStage stage) {
        return ResponseEntity.ok(service.updateCropStage(id, stage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrop(@PathVariable String id) {
        service.deleteCrop(id);
        return ResponseEntity.noContent().build();
    }
}
