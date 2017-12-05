package parking.lot;

public class TextPrinter implements ParkingServiceVisitor {
  private ParkingService service;
  private StringBuilder buff = new StringBuilder();

  public TextPrinter(ParkingService service) {
    this.service = service;
  }

  @Override
  public void visit(ParkingLot parkingLot) {
    buff.append("    " + "parking lot: ")
        .append("capacity = " + parkingLot.getCapacity() + ", ")
        .append("vacancy_num = " + parkingLot.getVacancyNum() + ", ")
        .append("vacancy_rate = " + parkingLot.getVacancyRate())
        .append("\n");
  }

  @Override
  public void visit(ParkingAssistant assistant) {
    buff.append("  " + "assistant: \n");
  }

  @Override
  public void visit(ParkingManager manager) {
    buff.append("manager: \n");
  }

  @Override
  public String toString() {
    service.accept(this);
    return buff.toString();
  }
}
