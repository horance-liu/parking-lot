package parking.lot;

public class ParkingAssistant extends MultiParkingService {
  public ParkingAssistant() {
    this(ParkingSelectors.sequential());
  }

  public ParkingAssistant(ParkingSelector selector) {
    super(selector);
  }

  @Override
  public void accept(ParkingServiceVisitor visitor) {
    visitor.visit(this);
    super.accept(visitor);
  }
}
