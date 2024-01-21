package sn.esmt.calculator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTestAssertJ {

    private Calculator calculator;
    private static Instant startedAt;

    @BeforeAll
    public static void initStartingTime() {
        System.out.println("Appel avant tous les tests");
        startedAt = Instant.now();
    }

    @AfterAll
    public static void showTestDuration() {
        System.out.println("Appel apres tous les tests");
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
    }

    @BeforeEach
    public void initCalculator() {
        System.out.println("Appel avant chaque test");
        calculator = new Calculator();
    }

    @AfterEach
    public void undefCalculator() {
        System.out.println("Appel apres chaque test");
        calculator = null ;
    }

    @Test
    void testAdd() {
        /*
              test add function
         */
        // arrange
        final double a = 12;
        final double b = 3;

        // act
        final double somme = calculator.add(a, b);

        //assert
        assertThat(somme).isEqualTo(15);
   }

   @Test
   void testMultiply() {
        /*
            test multiply function
         */
       // arrange
       final double a = 12;
       final double b = 3;

       // act
       final double product = calculator.multiply(a, b);

       //assert
       assertThat(product).isEqualTo(36);
    }

    @ParameterizedTest(name = "{0} x 0 doit être égal à 0")
    @ValueSource(ints = { 1, 2, 42, 1011, 5089 })
    public void multiply_shouldReturnZero_ofZeroWithMultipleIntegers(double arg) {
        // Arrange -- Tout est prêt !

        // Act -- Multiplier par zéro
        double actualResult = calculator.multiply(arg, 0);

        // Assert -- ça vaut toujours zéro !
        assertThat(actualResult).isEqualTo(0);
    }

    @ParameterizedTest(name = "{0} + {1} should equal to {2}")
    @CsvSource({ "1,1,2", "2,3,5", "42,57,99" })
    public void add_shouldReturnTheSum_ofMultipleIntegers(double arg1, double arg2, double expectResult) {
        // Arrange -- Tout est prêt !

        // Act
        double actualResult = calculator.add(arg1, arg2);

        // Assert
        assertThat(actualResult).isEqualTo(expectResult);
    }

    @Timeout(1)
    @Test
    public void longCalcul_shouldComputeInLessThan1Second() {
        // Arrange

        // Act
        calculator.longCalculation();

        // Assert
        // ...
    }

    @Test
    public void digitsSet_shouldReturnsTheSetOfDigits_ofPositiveInteger() {
        // GIVEN
        int number = 95897;

        // WHEN
        Set<Integer> actualDigits = calculator.digitsSet(number);

        // THEN
        assertThat(actualDigits).containsExactlyInAnyOrder(5, 7, 8, 9);
    }

    @Test
    public void digitsSet_shouldReturnsTheSetOfDigits_ofNegativeInteger() {
        // GIVEN
        int number = -15467;

        // WHEN
        Set<Integer> actualDigits = calculator.digitsSet(number);

        // THEN
        assertThat(actualDigits).containsExactlyInAnyOrder(7, 5, 1, 6, 4);
    }

    @Test
    public void digitsSet_shouldReturnsTheSetOfDigits_ofZeroInteger() {
        // GIVEN
        int number = 0;

        // WHEN
        Set<Integer> actualDigits = calculator.digitsSet(number);

        // THEN
        assertThat(actualDigits).containsExactly(0);
    }
}