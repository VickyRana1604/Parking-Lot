import Manager.DefaultParkingManager;
import Model.Slot.ParkingSlot;
import Model.Slot.SlotType;
import Model.Vehicle.Bike;
import Model.Vehicle.Car;
import Model.Vehicle.Truck;
import Model.Vehicle.Vehicle;

import java.util.List;

public class ParkingLotService {
    private ParkingSpace space;
    private String parkingLotId;
    ParkingLotService(){
        space=new ParkingSpace();
    }
    public String createParkingLot(String parkingLotId, int floors, int slotInEachFloor) {
        this.parkingLotId=parkingLotId;
        space.getLotMap().put(parkingLotId,new DefaultParkingManager(parkingLotId,floors,slotInEachFloor));
        return "Lot created successfully";
    }

    public String displayFreeSlots(String slotType) {

        String response="";
        int floorNo=1;
        for(List<ParkingSlot> freeSlots:space.getLotMap().get(parkingLotId).freeSlots(SlotType.valueOf(slotType))){
            response+="Free slots for "+slotType+" on floor"+(floorNo++)+" :";
            for(ParkingSlot slot:freeSlots){
                response+=slot.getSlotNo()+", ";
            }
            response+="\n";
        }
        return response;
    }

    public String displayOccSlots(String slotType) {
        String response="";
        int floorNo=1;
        for(Integer occSlots:space.getLotMap().get(parkingLotId).totOccSlots(SlotType.valueOf(slotType))){
            response+="Occupied slots for "+slotType+" on floor"+(floorNo++)+" : "+occSlots+"\n";
        }
        return response;
    }

    public String displayFreeSlotsCount(String slotType) {
        String response="";
        int floorNo=1;
        for(Integer freeSlots:space.getLotMap().get(parkingLotId).totFreeSlots(SlotType.valueOf(slotType))){
            response+="Total Free slots for "+slotType+" on floor"+(floorNo++)+" : "+freeSlots+"\n";
        }
        return response;
    }

    public String parkVehicle(String slotType, String regNo, String color) {

        Vehicle vehicle=null;
        switch (SlotType.valueOf(slotType)){
            case CAR:vehicle= Car.builder()
                    .regNo(regNo)
                    .colour(color)
                    .build();
            break;
            case TRUCK:vehicle= Truck.builder()
                    .regNo(regNo)
                    .colour(color)
                    .build();
                break;
            case BIKE:vehicle= Bike.builder()
                    .regNo(regNo)
                    .colour(color)
                    .build();
                break;
        }

        return "Vehicle susseccfully parked with ticket id: "+
                space.getLotMap().get(parkingLotId).parkVehicle(vehicle);
    }

    public String unparkVehicle(String ticketId) {
        space.getLotMap().get(parkingLotId).unparkVehicle(ticketId);
        return "Vehicle susseccfully unparked ";
    }
}
