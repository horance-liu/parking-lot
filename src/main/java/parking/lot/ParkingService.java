package parking.lot;

public interface ParkingService {
  boolean exist(ParkingCard card);
  int getVacancyNum();
  int getCapacity();

  ParkingCard park(Car car);
  Car take(ParkingCard card);

  void accept(ParkingServiceVisitor visitor);

  default boolean isFree() {
    return getVacancyNum() > 0;
  }

  default double getVacancyRate() {
    return getVacancyNum() * 1.0 / getCapacity();
  }
}
