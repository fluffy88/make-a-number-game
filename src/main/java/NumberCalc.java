import java.math.BigInteger;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

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
                BigInteger numberOne = BigInteger.valueOf(Long.parseLong(characters.get(i - 1)));
                String symbol = characters.get(i);
                BigInteger numberTwo = BigInteger.valueOf(Long.parseLong(characters.get(i + 1)));

                BiFunction<BigInteger, BigInteger, BigInteger> operator = MAP.get(symbol);
                BigInteger computed = operator.apply(numberOne, numberTwo);
                characters.remove(i + 1);
                characters.remove(i);
                characters.remove(--i);
                characters.add(i, computed.toString());
            }
        }
        return characters;
    }

    List<List<String>> generateAllSolutions(BigInteger... numbers) {
        List<List<String>> numberPermutations = recursiveGenerateAllNumberPermutations(numbers);
        List<List<String>> symbolPermutations = generateAllSymbolPermutations(numberPermutations.get(0));
        List<List<String>> possibilities = new ArrayList<>();
        for (List<String> numberPermutation : numberPermutations) {
            for (List<String> symbolPermutation : symbolPermutations) {
                List<String> sum = new ArrayList<>();
                Iterator<String> numIt = numberPermutation.iterator();
                Iterator<String> symIt = symbolPermutation.iterator();
                while (numIt.hasNext()) {
                    sum.add(numIt.next());
                    if (symIt.hasNext()) {
                        sum.add(symIt.next());
                    }
                }
                possibilities.add(sum);
            }
        }
        return possibilities;
    }

    List<List<String>> generateAllSymbolPermutations(List<String> word) {
        return recursiveGenerateAllSymbolPermutations(new ArrayList<>(), new ArrayList<>(), word.size());
    }

    private List<List<String>> recursiveGenerateAllSymbolPermutations(List<List<String>> permutations, List<String> permutation, int wordSize) {
        if (wordSize == 1) {
            permutations.add(new ArrayList<>(permutation));
            return permutations;
        }
        ArrayList<String> operators = new ArrayList<>(MAP.keySet());
        for (int i = 0; i < operators.size(); i++) {
            permutation.add(operators.get(i));
            recursiveGenerateAllSymbolPermutations(permutations, permutation, wordSize - 1);
            permutation.remove(permutation.size() - 1);
        }
        return permutations;
    }

    List<List<String>> recursiveGenerateAllNumberPermutations(BigInteger... numbers) {
        List<String> characters = Arrays.stream(numbers).map(BigInteger::toString).collect(Collectors.toList());
        return recursiveGenerateAllPermutations(new ArrayList<>(), new ArrayList<>(), characters);
    }

    private List<List<String>> recursiveGenerateAllPermutations(List<List<String>> permutations, List<String> permutation, List<String> characters) {
        if (characters.isEmpty()) {
            permutations.add(new ArrayList<>(permutation));
            return permutations;
        }
        for (int i = 0; i < characters.size(); i++) {
            permutation.add(characters.get(i));
            List<String> remainingCharacters = new ArrayList<>(characters);
            remainingCharacters.remove(i);
            recursiveGenerateAllPermutations(permutations, permutation, remainingCharacters);
            permutation.remove(permutation.size() - 1);
        }
        return permutations;
    }
}
