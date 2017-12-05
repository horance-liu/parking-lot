package parking.lot;

public class TextPrinterTest {
  public static void main(String[] args) {
    ParkingLot parking1 = new ParkingLot(20);
    ParkingLot parking2 = new ParkingLot(10);

    ParkingAssistant assistant1 = new ParkingAssistant(ParkingSelectors.maxVacancyNum());
    assistant1.add(parking1);
    assistant1.add(parking2);

    ParkingLot parking3 = new ParkingLot(20);
    ParkingLot parking4 = new ParkingLot(10);

    ParkingAssistant assistant2 = new ParkingAssistant(ParkingSelectors.maxVacancyRate());
    assistant2.add(parking3);
    assistant2.add(parking4);

    ParkingManager manager = new ParkingManager();
    manager.add(assistant1);
    manager.add(assistant2);

    manager.park(new Car("A2TX82"));
    manager.park(new Car("A66666"));

    TextPrinter printer = new TextPrinter(manager);
    System.out.println(printer.toString());
  }
}
