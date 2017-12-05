package parking.lot;

import java.util.Comparator;
import java.util.function.Function;

public final class ParkingSelectors {
  public static ParkingSelector sequential() {
    return services -> services.stream()
        .filter(ParkingService::isFree)
        .findFirst();
  }

  public static ParkingSelector maxVacancyNum() {
    return max(ParkingService::getVacancyNum);
  }

  public static ParkingSelector maxVacancyRate() {
    return max(ParkingService::getVacancyRate);
  }

  private static <U extends Comparable<? super U>>
  ParkingSelector max(Function<ParkingService, U> f) {
    return services -> services.stream()
        .max(Comparator.comparing(f));
  }

  public static ParkingSelector random() {
    return services -> services.stream()
        .filter(ParkingService::isFree)
        .collect(ShuffleCollector.toList()).stream()
        .findFirst();
  }

  private ParkingSelectors() {
  }
}