package parking.lot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot implements ParkingService {
  private int capacity;
  private Map<ParkingCard, Car> cars = new HashMap<>();

  public ParkingLot(int capacity) {
    this.capacity = capacity;
  }

  @Override
  public boolean exist(ParkingCard card) {
    return cars.containsKey(card);
  }

  @Override
  public int getVacancyNum() {
    return capacity - cars.size();
  }

  @Override
  public int getCapacity() {
    return capacity;
  }

  @Override
  public void accept(ParkingServiceVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public ParkingCard park(Car car) {
    if (!isFree())
      throw new ParkingLotException("parking lot is full");

    cars.put(car.getParkingCard(), car);
    return car.getParkingCard();
  }

  @Override
  public Car take(ParkingCard card) {
    if (!exist(card))
      throw new ParkingLotException("invalid parking card");

    Car car = cars.get(card);
    cars.remove(card);
    return car;
  }
}
