package Fibonacci

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class mainTest {

    @Test
    fun calcFibonacci() {
        assertEquals(55, calcFibonacci(10))
        assertEquals(6765, calcFibonacci(20))
        assertEquals(2, calcFibonacci(3))
        assertEquals(1, calcFibonacci(2))
        assertEquals(1, calcFibonacci(1))
        assertEquals(13, calcFibonacci(7))
        assertEquals(21, calcFibonacci(8))
    }
}