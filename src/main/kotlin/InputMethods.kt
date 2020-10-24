import java.io.File
import kotlin.system.exitProcess

fun readInstruction(line: String): Instruction {
    val lexems = line.split(" ").filterNot { it == "" }
    return when (lexems[0]) {
        "stop" -> Instruction.STOP
        "inc"  -> Instruction.INC(lexems[1])
        "dec"  -> Instruction.DEC(lexems[1])
        "zero" -> {
            if (lexems[3] != "else")
                throw Exception("Incorrect instruction - $line")
            Instruction.ZERO(lexems[1], lexems[2].toInt(), lexems[4].toInt())
        }
        else   -> throw Exception("Incorrect instruction - $line")
    }
}

fun readInstructions(fileName: String): List <Instruction> {
    val lines = File(fileName).readLines().filterNot { it == "" || it.startsWith("#") }
    try {
        return listOf(Instruction.STOP) + lines.map { readInstruction(it) }
    } catch (e: Exception) {
        println(e.message)
        exitProcess(0)
    }
}
