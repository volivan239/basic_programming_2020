package Fibonacci

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class mainTest {

    @Test
    fun calcFibonacci1st() {
        assertEquals(1, calcFibonacci(1))
    }
    @Test
    fun calcFibonacci10th() {
        assertEquals(55, calcFibonacci(10))
    }
    @Test
    fun calcFibonacci20th() {
        assertEquals(6765, calcFibonacci(20))
    }
    @Test
    fun calcFibonacci8th() {
        assertEquals(21, calcFibonacci(8))
    }
}