package lt.techin.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarNumberPricingCalculatorTest {

    CarNumberPricingCalculator calculator;
    double expectedPrice;

    @BeforeEach
    void createCalculator() {
        calculator = new CarNumberPricingCalculator();
        expectedPrice = 0;
    }

    @Test
    public void testPlatesWith3SameLettersCosts1000() {
        CarNumberPricingCalculator calculator = new CarNumberPricingCalculator();
        expectedPrice = calculator.calculatePrice("AAA123");
        assertEquals(1000, expectedPrice, "For plate number AAA123 price should be 1000.");
    }

    @Test
    public void testPlatesWith3SameDigitsCosts1000() {
        expectedPrice = calculator.calculatePrice("ABC333");
        assertEquals(1000, expectedPrice, "For plate number ABC333 price should be 1000.");
    }

    @Test
    public void testPlatesWith001Costs1000() {
        expectedPrice = calculator.calculatePrice("ABC001");
        assertEquals(1000, expectedPrice, "For plate number ABC001 price should be 1000.");
    }

    @Test
    public void testPlatesWith3SameLettersAnd3SameDigitsCosts5000() {
        expectedPrice = calculator.calculatePrice("AAA000");
        assertEquals(5000, expectedPrice, "For plate number AAA000 price should be 5000.");
    }

    @Test
    public void testInvalidNumberThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calculator.calculatePrice("Some string"));
        assertEquals("Incorrect plate number format. Must be 1-6 symbols: latin letters and number combination", exception.getMessage());
    }

    @Test
    public void testPlatesWithGODorKLRorBOSCost2000() {
        expectedPrice = calculator.calculatePrice("GOD123");
        assertEquals(2000, expectedPrice, "For plate number GOD123 price should be 2000.");
        expectedPrice = calculator.calculatePrice("KLR123");
        assertEquals(2000, expectedPrice, "For plate number KLR123 price should be 2000.");
        expectedPrice = calculator.calculatePrice("BOS123");
        assertEquals(2000, expectedPrice, "For plate number BOS123 price should be 2000.");
    }

    @Test
    public void testPlatesWithGODorKLRorBOSand3SameDigitsCost7000() {
        expectedPrice = calculator.calculatePrice("GOD000");
        assertEquals(7000, expectedPrice, "For plate number GOD000 price should be 7000.");
        expectedPrice = calculator.calculatePrice("KLR444");
        assertEquals(7000, expectedPrice, "For plate number KLR444 price should be 7000.");
        expectedPrice = calculator.calculatePrice("BOS333");
        assertEquals(7000, expectedPrice, "For plate number BOS333 price should be 7000.");
    }

    @Test
    public void testPersonalizedNumberCosts10000() {
        expectedPrice = calculator.calculatePrice("HELLO");
        assertEquals(10000, expectedPrice, "For plate number \"HELLO\" price should be 10000.");
    }

}
