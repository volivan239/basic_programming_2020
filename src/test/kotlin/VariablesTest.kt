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
        assertEquals(7, variables.set("x", 7).x)
    }

    @Test
    fun setIncorrect() {
        assertFails { variables.set("z", -1) }
    }

    @Test
    fun increase() {
        assertEquals(3, variables.increase("x").x)
    }

    @Test
    fun decrease() {
        assertEquals(8, variables.decrease("z").z)
    }

    @Test
    fun decreaseToZero() {
        var localVar = variables
        for (i in 0..10) {
            localVar = localVar.decrease("y")
        }
        assertEquals(0, localVar.y)
    }
}