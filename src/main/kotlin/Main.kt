import java.io.File
import kotlin.math.max

data class Variables(var x: Int = 0, var y: Int = 0, var z: Int = 0) {
    fun get(variableName: String): Int {
        return when (variableName) {
            "x" -> x
            "y" -> y
            "z" -> z
            else -> throw Exception("Incorrect variable")
        }
    }

    fun set(variableName: String, newVal: Int) {
        when (variableName) {
            "x" -> x = newVal
            "y" -> y = newVal
            "z" -> z = newVal
            else -> throw Exception("Incorrect variable")
        }
    }

    fun increase(variableName: String) {
        set(variableName, get(variableName) + 1)
    }

    fun decrease(variableName: String) {
        set(variableName, max(get(variableName) - 1, 0))
    }
}

data class State(var counter: Int, var variables: Variables)

sealed class Instruction {
    class INC(val variableName: String) : Instruction()
    class DEC(val variableName: String) : Instruction()
    object STOP : Instruction()
    class ZERO(val variableName: String, val whenTrue: Int, val whenFalse: Int) : Instruction()
}

fun processQuery(initial: String, instructions: List<Instruction>) {
    if (initial.toIntOrNull() == null || initial.toInt() < 0) {
        println("$initial is not a correct initial value, skipping")
        return
    }
    println("For initial value $initial")
    try {
        println("Result: ${run(initial.toInt(), instructions)}")
    } catch (e: Exception) {
        println("Run-time error: ${e.message}")
    }
}

fun main(args: Array <String>) {
    val instructions = readInstructions(args[0])
    println(instructions.size)
    File(args[1]).readLines().filterNot {it == "" }.forEach { processQuery(it, instructions) }
}