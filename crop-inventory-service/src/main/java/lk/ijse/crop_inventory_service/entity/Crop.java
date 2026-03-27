package lk.ijse.crop_inventory_service.entity;
import jakarta.persistence.*;
import lk.ijse.crop_inventory_service.constatns.CropStage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "crops")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CropStage stage;

    private int quantity; // optional: number of plants in this batch
}