import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class AlgoKtTest {

    private val state = State(3, Variables(4, 5, 6))

    @Test
    fun runInstructionStop() {
        assertEquals(true, runInstruction(state, Instruction.STOP))
        assertEquals(State(3, Variables(4, 5, 6)), state)
    }

    @Test
    fun runInstructionInc() {
        assertEquals(false, runInstruction(state, Instruction.INC("x")))
        assertEquals(State(4, Variables(5, 5, 6)), state)
    }

    @Test
    fun runInstructionDec() {
        assertEquals(false, runInstruction(state, Instruction.DEC("y")))
        assertEquals(State(4, Variables(4, 4, 6)), state)
    }

    @Test
    fun runInstructionZero() {
        assertEquals(false, runInstruction(state, Instruction.ZERO("z", 3, 5)))
        assertEquals(State(5, Variables(4, 5, 6)), state)
    }

    @Test
    fun runAlgorithm() {
        val algo = readAlgorithm(File("data/alg002.txt"))
        assertEquals(8, runAlgorithm(algo, 7))
    }
}