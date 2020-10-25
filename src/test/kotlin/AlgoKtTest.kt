import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class AlgoKtTest {

    @Test
    fun runAlgorithm() {
        val algo = readAlgorithm(File("data/alg001.txt"))
        assertEquals(Pair(1, 1), runAlgorithm(algo, 7, 9))
    }
}