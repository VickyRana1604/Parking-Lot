package Model.Slot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public abstract class ParkingSlot {
    private int floorNo;
    private int slotNo;
}
