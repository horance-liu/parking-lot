package parking.lot;

public interface ParkingServiceVisitor {
  void visit(ParkingLot parkingLot);
  void visit(ParkingAssistant assistant);
  void visit(ParkingManager manager);
}
