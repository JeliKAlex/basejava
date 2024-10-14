import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainJavaStream {
    private static final int[] values1 = {3, 2, 3, 1, 2, 3};
    private static final int[] values2 = {9, 8};
    private static final int[] values3 = {9, 1, 4, 5, 9, 8, 1};
    private static final List<Integer> integers1 = List.of(1, 2, 3, 4, 5);
    private static final List<Integer> integers2 = List.of(1, 1, 1, 2, 2, 1);
    private static final List<Integer> integers3 = List.of(1, 1, 1, 2, 2);

    public static void main(String[] args) {
        System.out.println(minValue(values1));
        System.out.println(minValue(values2));
        System.out.println(minValue(values3));
        System.out.println(oddOrEven(integers1));
        System.out.println(oddOrEven(integers2));
        System.out.println(oddOrEven(integers3));

    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (result, digit) -> result * 10 + digit);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream()
                .filter(integers.stream().mapToInt(Integer::intValue)
                        .sum() % 2 != 0 ? n -> n % 2 == 0 : n -> n % 2 != 0)
                .collect(Collectors.toList());
    }
}
