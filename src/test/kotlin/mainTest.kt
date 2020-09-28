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
        val a = listOf("a", "aba", "abacaba", "ax", "abc")
        assertEquals(a.filter { it.length > 2 }, a.filterWithFold { it.length > 2 })
    }
    @Test
    fun filterFoldEmpty() {
        val a = listOf(0, 1, 2, 3)
        assertEquals(a.filterWithFold{it < 0}, emptyList<Int>())
    }
    @Test
    fun filterFoldRight() {
        val a = listOf("a", "aba", "abacaba", "ax", "abc")
        assertEquals(a.filter { it.length > 2 }, a.filterWithFoldRight { it.length > 2 })
    }
    @Test
    fun filterFoldRightEmpty() {
        val a = listOf(0, 1, 2, 3)
        assertEquals(emptyList<Int>(), a.filterWithFoldRight{it < 0})
    }
    @Test
    fun lengths() {
        val arr = listOf("abacaba", "", "ab")
        val expected = listOf(7, 0, 2)
        assertEquals(expected, lengths(arr))
    }
    @Test
    fun sumsq1() {
        assertEquals(1, sumsq(1))
    }
    @Test
    fun sumsq() {
        val t = 1007
        var summ = 0
        for (i in 1..t)
            summ += i * i
        assertEquals(summ, sumsq(t))
    }
    @Test
    fun mapAccumLEx1() {
        val arr = listOf(9, 6, 3)
        val expected = Pair(5, listOf(45, 30, 15))
        assertEquals(expected, arr.mapAccumL({a, b -> Pair(a, a * b)}, 5))
    }
    @Test
    fun mapAccumLEx2() {
        val arr = listOf(2, 4, 8)
        val expected = Pair(19, listOf(10, 28, 88))
        assertEquals(expected, arr.mapAccumL({a, b -> Pair(a + b, a * b)}, 5))
    }
}