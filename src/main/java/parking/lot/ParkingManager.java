package parking.lot;

public class ParkingManager extends MultiParkingService {
  public ParkingManager() {
    super(ParkingSelectors.random());
  }

  @Override
  public void accept(ParkingServiceVisitor visitor) {
    visitor.visit(this);
    super.accept(visitor);
  }
}
