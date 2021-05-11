package Model.Vehicle;

import lombok.Builder;

public class Car extends Vehicle{
    @Builder
    public Car(String regNo, String colour) {
        super(regNo, colour);
    }
}
