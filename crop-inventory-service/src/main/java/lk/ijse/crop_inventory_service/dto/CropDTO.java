package lk.ijse.crop_inventory_service.dto;

import lk.ijse.crop_inventory_service.constatns.CropStage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CropDTO {

    private String name;
    private CropStage stage;
    private int quantity;
}
