package Model;

import lombok.Builder;

@Builder
public class Ticket {
    private int slotNo;
    private String parkingLotId;
    private int floorNo;

    public String getTicketId() {
        return parkingLotId + "_" + floorNo + "_" + slotNo;
    }
}
