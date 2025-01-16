package streams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class StreamGatherers {

    public static void main(String[] args) {
        // will contain: [[1, 2, 3], [4, 5, 6], [7, 8]]
        List<List<Integer>> windows = Stream
                .of(1,2,3,4,5,6,7,8)
                .gather(Gatherers.windowFixed(3)).toList();

        // will contain: [[1, 2], [2, 3], [3, 4], [4, 5], [5, 6], [6, 7], [7, 8]]
        List<List<Integer>> windows2 =
                Stream.of(1,2,3,4,5,6,7,8).gather(Gatherers.windowSliding(2)).toList();

        // will contain: [[1, 2, 3, 4, 5, 6], [2, 3, 4, 5, 6, 7], [3, 4, 5, 6, 7, 8]]
        List<List<Integer>> windows6 =
                Stream.of(1,2,3,4,5,6,7,8).gather(Gatherers.windowSliding(6)).toList();

        // will contain: Optional["123456789"]
        Optional<String> numberString = Stream.of(1,2,3,4,5,6,7,8,9)
                        .gather(Gatherers.fold(() -> "", (string, number) -> string + number))
                        .findFirst();

        // will contain: ["1", "12", "123", "1234", "12345", "123456", "1234567", "12345678", "123456789"]
        List<String> numberStrings = Stream.of(1,2,3,4,5,6,7,8,9)
                        .gather(Gatherers.scan(() -> "", (string, number) -> string + number))
                        .toList();

    }
}
