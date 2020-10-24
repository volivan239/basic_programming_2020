import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class AlgoKtTest {

    val state = State(3, Variables(4, 5, 6))

    @Test
    fun goStop() {
        assertEquals(true, go(state, Instruction.STOP))
        assertEquals(State(3, Variables(4, 5, 6)), state)
    }

    @Test
    fun goInc() {
        assertEquals(false, go(state, Instruction.INC("x")))
        assertEquals(State(4, Variables(5, 5, 6)), state)
    }

    @Test
    fun goDec() {
        assertEquals(false, go(state, Instruction.DEC("y")))
        assertEquals(State(4, Variables(4, 4, 6)), state)
    }

    @Test
    fun goZero() {
        assertEquals(false, go(state, Instruction.ZERO("z", 3, 5)))
        assertEquals(State(5, Variables(4, 5, 6)), state)
    }

    @Test
    fun run() {
        val algo = readInstructions(File("data/alg002.txt"))
        assertEquals(8, run(algo, 7))
    }
}