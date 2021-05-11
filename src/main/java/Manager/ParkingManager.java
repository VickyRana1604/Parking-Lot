package Manager;


import Exceptions.ParkingException;
import Model.Slot.*;
import Model.Ticket;
import Model.Vehicle.Car;
import Model.Vehicle.Truck;
import Model.Vehicle.Vehicle;

import java.util.*;

public abstract class ParkingManager {

    protected String parkingLotId;
    protected HashMap<SlotType, PriorityQueue<ParkingSlot>> availableSlot;
    protected HashMap<SlotType, HashMap<String, ParkingSlot>> occupiedSlot;
    protected int availableFloors;
    protected int slotsEachFloor;

    protected SlotType getSlotType(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            return SlotType.CAR;
        } else if (vehicle instanceof Truck) {
            return SlotType.TRUCK;
        } else {
            return SlotType.BIKE;
        }
    }

    protected void distributeSlots() {
        for (int floor = 1; floor <= availableFloors; floor++) {
            int slotNo = 1;
            if (slotsEachFloor >= 1) {
                availableSlot.get(SlotType.TRUCK).add(TruckParkingSlot.builder().floorNo(floor).slotNo(slotNo++).build());
            }
            if (slotsEachFloor > 1) {
                while (slotNo <= Math.min(3, slotsEachFloor)) {
                    availableSlot.get(SlotType.BIKE).add(BikeParkingSlot.builder().floorNo(floor).slotNo(slotNo++).build());
                }
            }
            if (slotsEachFloor > 3) {
                while (slotNo <= slotsEachFloor) {
                    availableSlot.get(SlotType.CAR).add(CarParkingSlot.builder().floorNo(floor).slotNo(slotNo++).build());
                }
            }
        }
    }

    protected int priortiseSlot(ParkingSlot slot1, ParkingSlot slot2) {
        if (slot1.getFloorNo() != slot2.getFloorNo()) return Integer.compare(slot1.getFloorNo(), slot2.getFloorNo());
        return Integer.compare(slot1.getFloorNo(), slot2.getFloorNo());
    }

    protected ParkingManager(String parkingLotId, int availableFloors, int slotsEachFloor) {
        this.availableFloors = availableFloors;
        this.slotsEachFloor = slotsEachFloor;
        this.parkingLotId = parkingLotId;
        availableSlot = new HashMap<>();
        occupiedSlot = new HashMap<>();
        for (SlotType type : SlotType.values()) {
            availableSlot.put(type, new PriorityQueue<>(this::priortiseSlot));
            occupiedSlot.put(type, new HashMap<>());
        }
        distributeSlots();
    }

    public String parkVehicle(Vehicle vehicle) throws ParkingException {
        ParkingSlot availableParkingSlot = availableSlot.get(getSlotType(vehicle)).poll();
        if (availableParkingSlot==null) {
            throw new ParkingException("Parking Lot Full");
        }
        String ticketId = Ticket.builder()
                .floorNo(availableParkingSlot.getFloorNo())
                .slotNo(availableParkingSlot.getSlotNo())
                .parkingLotId(parkingLotId).build().getTicketId();
        occupiedSlot.get(getSlotType(vehicle)).put(ticketId, availableParkingSlot);
        return ticketId;
    }

    public void unparkVehicle(String ticketId) {
        ParkingSlot occupied;
        SlotType slotType = null;
        for (SlotType type : SlotType.values()) {
            if (occupiedSlot.get(type).get(ticketId) != null) {
                slotType = type;
            }
        }
        if (slotType == null) {
            throw new ParkingException("Wrong ticket Id");
        }
        occupied = occupiedSlot.get(slotType).get(ticketId);
        occupiedSlot.get(slotType).remove(ticketId);
        availableSlot.get(slotType).add(occupied);
    }

    public List<List<ParkingSlot>> freeSlots(SlotType type) {
        List<List<ParkingSlot>> lists = new ArrayList<>();
        for (int floor = 1; floor <= availableFloors; floor++) {
            lists.add(floor - 1, new ArrayList<>());
        }
        for (ParkingSlot slot : availableSlot.get(type)) {
            lists.get(slot.getFloorNo() - 1).add(slot);
        }
        return lists;
    }

    public int[] totFreeSlots(SlotType type) {
        int[] totFreeSlots = new int[availableFloors];
        for (int floor = 1; floor <= availableFloors; floor++) {
            totFreeSlots[floor - 1] = 0;
        }
        for (ParkingSlot slot : availableSlot.get(type)) {
            totFreeSlots[slot.getFloorNo() - 1]++;
        }
        return totFreeSlots;
    }

    public int[] totOccSlots(SlotType type) {
        int[] totOccSlots = new int[availableFloors];
        for (int floor = 1; floor <= availableFloors; floor++) {
            totOccSlots[floor - 1] = 0;
        }
        for (Map.Entry<String, ParkingSlot> entry : occupiedSlot.get(type).entrySet()) {
            totOccSlots[entry.getValue().getFloorNo() - 1]++;
        }
        return totOccSlots;
    }
}
