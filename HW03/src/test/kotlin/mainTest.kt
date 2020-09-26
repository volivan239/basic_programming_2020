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
    @Test
    fun reverseEmpty() {
        assertEquals(emptyList<Int>(), reverse(emptyList<Int>()))
    }
    @Test
    fun reverseRandom() {
        val list = listOf<Int>(1, 2, 3, 4, 5)
        val expected = listOf<Int>(1, 2, 3, 4, 5)
        assertEquals(expected, list)
    }
    @Test
    fun filterFold() {
        var a = listOf("a", "aba", "abacaba", "ax", "abc")
        assertEquals(a.filter { it.length > 2 }, a.filterWithFold { it.length > 2 })
    }
    @Test
    fun filterFoldEmpty() {
        var a = listOf(0, 1, 2, 3)
        assertEquals(a.filterWithFold{it < 0}, emptyList<Int>())
    }
    @Test
    fun filterFoldRight() {
        var a = listOf("a", "aba", "abacaba", "ax", "abc")
        assertEquals(a.filter { it.length > 2 }, a.filterWithFoldRight { it.length > 2 })
    }
    @Test
    fun filterFoldRightEmpty() {
        var a = listOf(0, 1, 2, 3)
        assertEquals(emptyList<Int>(), a.filterWithFoldRight{it < 0})
    }
}