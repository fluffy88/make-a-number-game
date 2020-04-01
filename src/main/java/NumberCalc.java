import java.math.BigInteger;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumberCalc {

    private static Map<String, BiFunction<BigInteger, BigInteger, BigInteger>> MAP = new HashMap<>();

    static {
        MAP.put("+", BigInteger::add);
        MAP.put("-", BigInteger::subtract);
        MAP.put("*", BigInteger::multiply);
        MAP.put("/", BigInteger::divide);
    }

    public String calc(BigInteger result, BigInteger... numbers) {
        List<List<String>> possibilities = generateAllSolutions(numbers);
        return findMatchingResult(result, possibilities);
    }

    private String findMatchingResult(BigInteger result, List<List<String>> possibilities) {
        return possibilities.stream()
                .filter(characters -> {
                    List<String> replaced = compute(characters, "*", "/");
                    replaced = compute(replaced, "+", "-");
                    return replaced.stream()
                            .reduce(String::concat)
                            .map(Long::parseLong)
                            .map(BigInteger::valueOf)
                            .orElse(BigInteger.ZERO)
                            .equals(result);
                })
                .findFirst()
                .orElse(Collections.emptyList())
                .stream()
                .reduce(String::concat)
                .orElse("");
    }

    List<String> compute(List<String> input, String... symbols) {
        List<String> characters = new ArrayList<>(input);
        for (int i = 0; i < characters.size(); i++) {
            if (Arrays.asList(symbols).contains(characters.get(i))) {
                BigInteger numberOne = BigInteger.valueOf(Long.parseLong(characters.get(i-1)));
                String symbol = characters.get(i);
                BigInteger numberTwo = BigInteger.valueOf(Long.parseLong(characters.get(i+1)));

                BiFunction<BigInteger, BigInteger, BigInteger> operator = MAP.get(symbol);
                BigInteger computed = operator.apply(numberOne, numberTwo);
                characters.remove(i+1);
                characters.remove(i);
                characters.remove(--i);
                characters.add(i, computed.toString());
            }
        }
        return characters;
    }

    List<List<String>> generateAllSolutions(BigInteger... numbers) {
        List<List<String>> possibilities = new ArrayList<>();
        List<String> operators = new ArrayList<>(MAP.keySet());
        for (int f1 = 0; f1 < operators.size(); f1++) {
            for (int n1 = 0; n1 < numbers.length; n1++) {
                List<String> startOfPossibility = new ArrayList<>();
                startOfPossibility.add(numbers[n1].toString());
                startOfPossibility.add(operators.get(f1));
                for (int f2 = 0; f2 < operators.size(); f2++) {
                    List<String> possibility = new ArrayList<>(startOfPossibility);
                    for (int n2 = 0; n2 < numbers.length; n2++) {
                        if (n2 != n1) {
                            possibility.add(numbers[n2].toString());
                            possibility.add(operators.get(f2));
                        }
                    }
                    List<String> computed = possibility.subList(0, possibility.size() - 1);
                    if (!possibilities.contains(computed)) {
                        possibilities.add(computed);
                    }
                }
            }
        }
        return possibilities;
    }

}
