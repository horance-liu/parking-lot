package parking.lot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

class MultiParkingService implements ParkingService {
  private List<ParkingService> services = new ArrayList<>();
  private ParkingSelector selector;

  protected MultiParkingService(ParkingSelector selector) {
    this.selector = selector;
  }

  public void add(ParkingService service) {
    services.add(service);
  }

  @Override
  public boolean exist(ParkingCard card) {
    return stream().anyMatch(s -> s.exist(card));
  }

  @Override
  public int getVacancyNum() {
    return sum(ParkingService::getVacancyNum);
  }

  @Override
  public int getCapacity() {
    return sum(ParkingService::getCapacity);
  }

  private int sum(ToIntFunction<ParkingService> mapper) {
    return stream()
        .mapToInt(mapper)
        .reduce(0, Integer::sum);
  }

  @Override
  public void accept(ParkingServiceVisitor visitor) {
    stream().forEach(s -> s.accept(visitor));
  }

  @Override
  public ParkingCard park(Car car) {
    return selector.select(services)
        .map(s -> s.park(car))
        .orElseThrow(exception("can't park car in all the parking lots "));
  }

  @Override
  public Car take(ParkingCard card) {
    return stream()
        .filter(s -> s.exist(card))
        .map(s -> s.take(card))
        .findFirst()
        .orElseThrow(exception("not found in all of the parking lots"));
  }

  private static Supplier<ParkingLotException> exception(String msg) {
    return () -> new ParkingLotException(msg);
  }

  private Stream<ParkingService> stream() {
    return services.stream();
  }
}
