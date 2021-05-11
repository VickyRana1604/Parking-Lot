import Manager.ParkingManager;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class ParkingSpace {
    private HashMap<String, ParkingManager> lotMap;

    public ParkingSpace() {
        lotMap = new HashMap<>();
    }
}
