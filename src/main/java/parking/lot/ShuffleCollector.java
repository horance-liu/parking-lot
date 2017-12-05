package parking.lot;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class ShuffleCollector {
  private static final Collector<?, ?, ?> SHUFFLER =
      Collectors.collectingAndThen(Collectors.toList(), list -> {
        Collections.shuffle(list);
        return list;
      });

  @SuppressWarnings("unchecked")
  public static <T> Collector<T, ?, List<T>> toList() {
    return (Collector<T, ?, List<T>>) SHUFFLER;
  }

  private ShuffleCollector() {
  }
}
