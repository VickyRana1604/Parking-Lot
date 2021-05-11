package Model.Slot;

import lombok.Builder;

public class CarParkingSlot extends ParkingSlot {
    @Builder
    public CarParkingSlot(int floorNo, int slotNo) {
        super(floorNo, slotNo);
    }
}
