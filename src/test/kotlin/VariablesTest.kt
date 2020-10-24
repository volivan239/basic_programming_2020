import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.test.*

internal class VariablesTest {

    private val variables = Variables(2, 3, 9)

    @Test
    fun get() {
        assertEquals(2, variables.get("x"))
        assertEquals(3, variables.get("y"))
        assertEquals(9, variables.get("z"))
        assertFails { variables.get("a") }
    }

    @Test
    fun set() {
        variables.set("x", 7)
        assertEquals(7, variables.x)
    }

    @Test
    fun setIncorrect() {
        assertFails { variables.set("z", -1) }
    }

    @Test
    fun increase() {
        variables.increase("x")
        assertEquals(3, variables.x)
    }

    @Test
    fun decrease() {
        variables.decrease("z")
        assertEquals(8, variables.z)
    }

    @Test
    fun decreaseToZero() {
        for (i in 0..10) {
            variables.decrease("y")
        }
        assertEquals(0, variables.y)
    }
}