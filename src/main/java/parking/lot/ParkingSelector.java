package parking.lot;

import java.util.List;
import java.util.Optional;

@FunctionalInterface
public interface ParkingSelector {
  Optional<ParkingService> select(List<ParkingService> services);
}
