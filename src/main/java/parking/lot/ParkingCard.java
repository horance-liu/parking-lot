package parking.lot;

public class ParkingCard {
  private String id;

  public ParkingCard(String id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ParkingCard) {
      ParkingCard other = (ParkingCard) obj;
      return id.equals(other.id);
    }
    return false;
  }
}
