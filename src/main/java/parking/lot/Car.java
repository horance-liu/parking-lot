package parking.lot;

public class Car {
  private ParkingCard card;

  public Car(String id) {
    card = new ParkingCard(id);
  }

  public ParkingCard getParkingCard() {
    return card;
  }
}
