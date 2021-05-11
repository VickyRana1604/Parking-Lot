package Model.Slot;

import lombok.Builder;

public class BikeParkingSlot extends ParkingSlot {
    @Builder
    public BikeParkingSlot(int floorNo, int slotNo) {
        super(floorNo, slotNo);
    }
}
