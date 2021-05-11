import java.util.Scanner;

public class Main {
    private final static String CreatingParkingLot = "create_parking_lot";
    private final static String display = "display";
    private final static String freeCount = "free_count";
    private final static String freeSlots = "free_slots";
    private final static String occupiedSlots = "occupied_slots";
    private final static String parkVehicle = "park_vehicle";
    private final static String unparkVehicle = "unpark_vehicle";
    private final static String exit = "exit";

    private static String run(ParkingLotService service, String[] args) throws Exception {
        int i = 0;
        switch (args[i++]) {
            case CreatingParkingLot:
                return service.createParkingLot(args[i++],
                        Integer.parseInt(args[i++]),
                        Integer.parseInt(args[i++]));
            case display:
                switch (args[i++]) {
                    case freeCount:
                        return service.displayFreeSlotsCount(args[i++]);
                    case freeSlots:
                        return service.displayFreeSlots(args[i++]);
                    case occupiedSlots:
                        return service.displayOccSlots(args[i++]);
                }
            case parkVehicle:
                return service.parkVehicle(args[i++], args[i++], args[i++]);
            case unparkVehicle:
                return service.unparkVehicle(args[i++]);
            case exit:
                System.exit(0);
        }
        throw new Exception("Incorrect command");

    }

    public static void main(String[] args) throws Exception {

        ParkingLotService service = new ParkingLotService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your command");
            try {
                System.out.println(run(service, scanner.nextLine().split(" ")));
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }
}
