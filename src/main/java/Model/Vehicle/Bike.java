package Model.Vehicle;

import lombok.Builder;

public class Bike extends Vehicle{
    @Builder
    public Bike(String regNo, String colour) {
        super(regNo, colour);
    }
}
