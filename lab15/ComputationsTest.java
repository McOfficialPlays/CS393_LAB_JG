package week15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ComputationsTest {

    @Test
    void testFibonacciBaseCases() {
	assertEquals(0, Computations.fibonacci(0));
	assertEquals(1, Computations.fibonacci(1));
    }
    @Test
    void testFibonacciNominalValues() {
	assertEquals(1, Computations.fibonacci(2));
	assertEquals(2, Computations.fibonacci(3));
        assertEquals(3, Computations.fibonacci(4));
    
    }
    @Test
    void testFibonacciNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            Computations.fibonacci(-1);
        });
    }
    @Test
    void testIsPrimeSmallValues() {
        assertFalse(Computations.isPrime(-5));
        assertFalse(Computations.isPrime(0));
        assertFalse(Computations.isPrime(1));
        assertTrue(Computations.isPrime(2));
    }
    @Test
    void testIsPrimePrimeNumbers() {
        assertTrue(Computations.isPrime(5));
        assertTrue(Computations.isPrime(7));
        assertTrue(Computations.isPrime(11));
       
    }
    @Test
    void testIsEven() {
        assertTrue(Computations.isEven(2));
        assertTrue(Computations.isEven(-4));
        assertFalse(Computations.isEven(1));
        assertFalse(Computations.isEven(-3));
    }
    @Test
    void testIsOdd() {
        assertTrue(Computations.isOdd(1));
        assertTrue(Computations.isOdd(-3));
        assertFalse(Computations.isOdd(2));
        assertFalse(Computations.isOdd(0));
        
    }
    @Test
    void testToCelsius() {
        assertEquals(0.0, Computations.toCelsius(32.0), 0.001);
        assertEquals(100.0, Computations.toCelsius(212.0), 0.001);
        assertEquals(-40.0, Computations.toCelsius(-40.0), 0.001);
        assertEquals(37.777, Computations.toCelsius(100.0), 0.001);
    }
    @Test
    void testToFahrenheit() {
        assertEquals(32.0, Computations.toFahrenheit(0.0), 0.001);
        assertEquals(212.0, Computations.toFahrenheit(100.0), 0.001);
        assertEquals(-40.0, Computations.toFahrenheit(-40.0), 0.001);
        assertEquals(98.6, Computations.toFahrenheit(37.0), 0.001);
    }
}
