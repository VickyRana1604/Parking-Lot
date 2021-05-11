package Model.Slot;

import lombok.Builder;

public class TruckParkingSlot extends ParkingSlot {
    @Builder
    public TruckParkingSlot(int floorNo, int slotNo) {
        super(floorNo, slotNo);
    }
}
