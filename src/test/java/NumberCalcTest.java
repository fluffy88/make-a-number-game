import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class NumberCalcTest {

    @Test
    public void twoNumberAddTest() {
        NumberCalc calc = new NumberCalc();

        String actual = calc.calc(BigInteger.valueOf(5), BigInteger.valueOf(2), BigInteger.valueOf(3));

        assertEquals("2+3", actual);
    }

    @Test
    public void twoNumberSubtractTest() {
        NumberCalc calc = new NumberCalc();

        String actual = calc.calc(BigInteger.valueOf(2), BigInteger.valueOf(5), BigInteger.valueOf(7));

        assertEquals("7-5", actual);
    }

    @Test
    public void twoNumberMultiplyTest() {
        NumberCalc calc = new NumberCalc();

        String actual = calc.calc(BigInteger.valueOf(6), BigInteger.valueOf(2), BigInteger.valueOf(3));

        assertEquals("2*3", actual);
    }

    @Test
    public void threeNumberAddTest() {
        NumberCalc calc = new NumberCalc();

        String actual = calc.calc(BigInteger.valueOf(9), BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(4));

        assertEquals("2+3+4", actual);
    }

    @Test
    public void threeNumberSubtractTest() {
        NumberCalc calc = new NumberCalc();

        String actual = calc.calc(BigInteger.valueOf(7), BigInteger.valueOf(3), BigInteger.valueOf(5), BigInteger.valueOf(15));

        assertEquals("15-3-5", actual);
    }

    @Test
    public void threeNumberMultiplyTest() {
        NumberCalc calc = new NumberCalc();

        String actual = calc.calc(BigInteger.valueOf(24), BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(4));

        assertEquals("2*3*4", actual);
    }

    @Test
    public void threeNumberAddMultiplyTest() {
        NumberCalc calc = new NumberCalc();

        String actual = calc.calc(BigInteger.valueOf(14), BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(4));

        assertEquals("2+3*4", actual);
    }

    @Test
    public void fourNumberMultiplyAddAddTest() {
        NumberCalc calc = new NumberCalc();

        String actual = calc.calc(BigInteger.valueOf(64), BigInteger.valueOf(2), BigInteger.valueOf(9), BigInteger.valueOf(16), BigInteger.valueOf(30));

        assertEquals("2*9+16+30", actual);
    }

    @Test
    public void fourNumberDivideMultiplyAddTest() {
        NumberCalc calc = new NumberCalc();

        String actual = calc.calc(BigInteger.valueOf(151), BigInteger.valueOf(2), BigInteger.valueOf(9), BigInteger.valueOf(16), BigInteger.valueOf(30));

        assertEquals("9*30/2+16", actual);
    }

    @Test
    public void stephenTest() {
        NumberCalc calc = new NumberCalc();

        String actual = calc.calc(BigInteger.valueOf(813), BigInteger.valueOf(1), BigInteger.valueOf(10), BigInteger.valueOf(25), BigInteger.valueOf(50), BigInteger.valueOf(75), BigInteger.valueOf(100));

        assertEquals("1 10 25 50 75 100", actual);
    }

    @Test
    @Tag("ThisOne")
    public void twoNumberGenerateTest() {
        NumberCalc calc = new NumberCalc();

        List<List<String>> actual = calc.generateAllSolutions(BigInteger.valueOf(2), BigInteger.valueOf(3));

        assertEquals(8, actual.size());
        assertTrue(actual.contains(Arrays.asList("2+3".split(""))));
        assertTrue(actual.contains(Arrays.asList("2-3".split(""))));
        assertTrue(actual.contains(Arrays.asList("2*3".split(""))));
        assertTrue(actual.contains(Arrays.asList("2/3".split(""))));
        assertTrue(actual.contains(Arrays.asList("3+2".split(""))));
        assertTrue(actual.contains(Arrays.asList("3-2".split(""))));
        assertTrue(actual.contains(Arrays.asList("3*2".split(""))));
        assertTrue(actual.contains(Arrays.asList("3/2".split(""))));
    }

    @Test
    @Tag("ThisOne")
    public void fourNumberGenerateDivideMultiplyAddTest() {
        NumberCalc calc = new NumberCalc();

        List<List<String>> actual = calc.generateAllSolutions(BigInteger.valueOf(2), BigInteger.valueOf(9), BigInteger.valueOf(16), BigInteger.valueOf(30));

        List<String> expected = Arrays.asList("30", "/", "2", "*", "9", "+", "16");
        assertTrue(actual.contains(expected));
    }

    @Test
    @Tag("ThisOne")
    public void fourNumberGenerateDivideMultiplyMultiplyTest() {
        NumberCalc calc = new NumberCalc();

        List<List<String>> actual = calc.generateAllSolutions(BigInteger.valueOf(2), BigInteger.valueOf(9), BigInteger.valueOf(16), BigInteger.valueOf(30));

        List<String> expected = Arrays.asList("30", "/", "2", "*", "9", "*", "16");
        assertTrue(actual.contains(expected));
    }

    @Test
    @Tag("ThisOne")
    public void threeNumberGenerateTest() {
        NumberCalc calc = new NumberCalc();

        List<List<String>> actual = calc.generateAllSolutions(BigInteger.valueOf(5), BigInteger.valueOf(6), BigInteger.valueOf(8));

        assertEquals(96, actual.size());
        assertTrue(actual.contains(Arrays.asList("5+6+8".split(""))));
        assertTrue(actual.contains(Arrays.asList("5+8+6".split(""))));
        assertTrue(actual.contains(Arrays.asList("5+6-8".split(""))));
        assertTrue(actual.contains(Arrays.asList("5+6*8".split(""))));
        assertTrue(actual.contains(Arrays.asList("5+6/8".split(""))));
        assertTrue(actual.contains(Arrays.asList("5+8-6".split(""))));
        assertTrue(actual.contains(Arrays.asList("5+8*6".split(""))));
        assertTrue(actual.contains(Arrays.asList("5+8/6".split(""))));
        assertTrue(actual.contains(Arrays.asList("8*5*6".split(""))));
        assertTrue(actual.contains(Arrays.asList("8*5+6".split(""))));
        assertTrue(actual.contains(Arrays.asList("8*6*5".split(""))));
        assertTrue(actual.contains(Arrays.asList("8*6+5".split(""))));
        assertTrue(actual.contains(Arrays.asList("8+5*6".split(""))));
        assertTrue(actual.contains(Arrays.asList("8+5+6".split(""))));
        assertTrue(actual.contains(Arrays.asList("8+6*5".split(""))));
        assertTrue(actual.contains(Arrays.asList("8+6*5".split(""))));
    }

    @Test
    public void threeNumberRecursiveGenerateTest() {
        NumberCalc calc = new NumberCalc();

        List<List<String>> actual = calc.recursiveGenerateAllNumberPermutations(BigInteger.valueOf(1), BigInteger.valueOf(2), BigInteger.valueOf(3));

        assertEquals(6, actual.size());
        assertTrue(actual.contains(Arrays.asList("123".split(""))));
        assertTrue(actual.contains(Arrays.asList("132".split(""))));
        assertTrue(actual.contains(Arrays.asList("213".split(""))));
        assertTrue(actual.contains(Arrays.asList("231".split(""))));
        assertTrue(actual.contains(Arrays.asList("312".split(""))));
        assertTrue(actual.contains(Arrays.asList("321".split(""))));
    }

    @Test
    public void threeNumberRecursiveSymbolGenerateTest() {
        NumberCalc calc = new NumberCalc();

        List<List<String>> actual = calc.generateAllSymbolPermutations(Arrays.asList("1", "2", "3"));

        assertEquals(16, actual.size());
        assertTrue(actual.contains(Arrays.asList("++".split(""))));
        assertTrue(actual.contains(Arrays.asList("+-".split(""))));
        assertTrue(actual.contains(Arrays.asList("+*".split(""))));
        assertTrue(actual.contains(Arrays.asList("+/".split(""))));
        assertTrue(actual.contains(Arrays.asList("-+".split(""))));
        assertTrue(actual.contains(Arrays.asList("--".split(""))));
        assertTrue(actual.contains(Arrays.asList("-*".split(""))));
        assertTrue(actual.contains(Arrays.asList("-/".split(""))));
        assertTrue(actual.contains(Arrays.asList("*+".split(""))));
        assertTrue(actual.contains(Arrays.asList("*-".split(""))));
        assertTrue(actual.contains(Arrays.asList("**".split(""))));
        assertTrue(actual.contains(Arrays.asList("*/".split(""))));
        assertTrue(actual.contains(Arrays.asList("/+".split(""))));
        assertTrue(actual.contains(Arrays.asList("/-".split(""))));
        assertTrue(actual.contains(Arrays.asList("/*".split(""))));
        assertTrue(actual.contains(Arrays.asList("//".split(""))));
    }

    @Test
    public void fourNumberRecursiveSymbolGenerateTest() {
        NumberCalc calc = new NumberCalc();

        List<List<String>> actual = calc.generateAllSymbolPermutations(Arrays.asList("1", "2", "3", "4"));

        assertEquals(64, actual.size());
    }

    @Test
    public void twoNumberComputeMultiplyTest() {
        NumberCalc calc = new NumberCalc();

        List<String> actual = calc.compute(new ArrayList<>(Arrays.asList("4", "*", "6")), "*");

        assertEquals(Collections.singletonList("24"), actual);
    }

    @Test
    public void threeNumberComputeSubtractAddTest() {
        NumberCalc calc = new NumberCalc();

        List<String> actual = calc.compute(new ArrayList<>(Arrays.asList("15", "-", "3", "+", "5")), "+", "-");

        assertEquals(Collections.singletonList("17"), actual);
    }

    @Test
    public void threeNumberComputeMultiplyTest() {
        NumberCalc calc = new NumberCalc();

        List<String> actual = calc.compute(new ArrayList<>(Arrays.asList("2", "*", "4", "*", "6")), "*");

        assertEquals(Collections.singletonList("48"), actual);
    }

    @Test
    public void fourNumberComputeMultiplyTest() {
        NumberCalc calc = new NumberCalc();

        List<String> actual = calc.compute(new ArrayList<>(Arrays.asList("3", "*", "2", "*", "10", "*", "5")), "*");

        assertEquals(Collections.singletonList("300"), actual);
    }

    @Test
    public void fourNumberComputeMultiplyWithAddTest() {
        NumberCalc calc = new NumberCalc();

        List<String> actual = calc.compute(new ArrayList<>(Arrays.asList("3", "*", "2", "+", "10", "*", "5")), "*");

        assertEquals(Arrays.asList("6", "+", "50"), actual);
    }

    @Test
    public void fourNumberComputeAddWithMultiplyTest() {
        NumberCalc calc = new NumberCalc();

        List<String> actual = calc.compute(new ArrayList<>(Arrays.asList("3", "*", "2", "+", "10", "*", "5")), "+");

        assertEquals(Arrays.asList("3", "*", "12", "*", "5"), actual);
    }

}