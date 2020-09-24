import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class mainTest {

    @Test
    fun emptyList() {
        assertEquals(listOf<Int>(), qsort(listOf<Int>()))
    }
    @Test
    fun oneElement() {
        val list = listOf<Int>(-2)
        assertEquals(listOf(-2), qsort(list))
    }
    @Test
    fun sameElements() {
        val list = listOf<Int>(1, 1, 1, 1, -1, 1)
        val expected = listOf<Int>(-1, 1, 1, 1, 1, 1)
        assertEquals(expected, qsort(list))
    }
    @Test
    fun randArray() {
        val list = listOf<Int>(20, -20, 0, 3, 7, 5, 1, 0)
        val expected = listOf<Int>(-20, 0, 0, 1, 3, 5, 7, 20)
        assertEquals(expected, qsort(list))
    }
}