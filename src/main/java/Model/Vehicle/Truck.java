package Model.Vehicle;

import lombok.Builder;

public class Truck extends Vehicle{
    @Builder
    public Truck(String regNo, String colour) {
        super(regNo, colour);
    }
}
