package Model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Vehicle {
    protected String regNo;
    protected String colour;
}
