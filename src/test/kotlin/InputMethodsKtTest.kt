import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File
import kotlin.test.assertFails

internal class InputMethodsKtTest {

    @Test
    fun parseInstructionStop() {
        assertEquals(Instruction.STOP, parseInstruction("stop"))
    }

    @Test
    fun parseInstructionInc() {
        assertEquals(Instruction.INC("y"), parseInstruction("inc y"))
    }

    @Test
    fun parseInstructionDecWithComment() {
        assertEquals(Instruction.DEC("z"), parseInstruction("dec z // random comment"))
    }

    @Test
    fun parseInstructionZero() {
        assertEquals(Instruction.ZERO("x", 3, 4), parseInstruction("zero x 3 else 4"))
    }

    @Test
    fun parseInstructionIncorrectZero() {
        assertFails { parseInstruction("zero x 3 4") }
    }

    @Test
    fun readAlgorithm() {
        val actual = readAlgorithm(File("data/alg002.txt"))
        val expected = listOf(
            Instruction.STOP,
            Instruction.ZERO("x", 5, 2),
            Instruction.INC("y"),
            Instruction.DEC("x"),
            Instruction.ZERO("x", 5, 2),
            Instruction.INC("y"),
            Instruction.STOP
        )
        assertEquals(expected, actual)
    }
}