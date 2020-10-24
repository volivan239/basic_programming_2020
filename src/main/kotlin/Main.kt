import java.io.File
import kotlin.math.max

// Stores variables' values
data class Variables(var x: Int = 0, var y: Int = 0, var z: Int = 0) {
    // Returns value of $variableName variable
    fun get(variableName: String): Int {
        return when (variableName) {
            "x" -> x
            "y" -> y
            "z" -> z
            else -> throw Exception("Incorrect variable - $variableName")
        }
    }

    // Sets value of $variableName variable to $newVal
    fun set(variableName: String, newVal: Int) {
        if (newVal < 0) {
            throw Exception("Inccorrect value - $newVal")
        }
        when (variableName) {
            "x" -> x = newVal
            "y" -> y = newVal
            "z" -> z = newVal
            else -> throw Exception("Incorrect variable - $variableName")
        }
    }

    // Increases $variableName variable by one (command inc in algorithm)
    fun increase(variableName: String) {
        set(variableName, get(variableName) + 1)
    }

    // Decreases $variableName variable by one (command dec in algorithm)
    fun decrease(variableName: String) {
        set(variableName, max(get(variableName) - 1, 0))
    }
}

// Stores program state
data class State(var counter: Int, var variables: Variables)

// Stores one instruction (i.e. step / line) of algorithm
sealed class Instruction {
    data class INC(val variableName: String) : Instruction()
    data class DEC(val variableName: String) : Instruction()
    object STOP : Instruction()
    data class ZERO(val variableName: String, val whenTrue: Int, val whenFalse: Int) : Instruction()
}

// Runs algorithm on one initial value
fun processQuery(initial: String, instructions: List<Instruction>) {
    if (initial.toIntOrNull() == null || initial.toInt() < 0) {
        println("$initial is not a correct initial value, skipping")
        return
    }
    println("For initial value $initial")
    try {
        println("Result: ${run(instructions, initial.toInt())}")
    } catch (e: Exception) {
        println("Run-time error: ${e.message}")
    }
}

fun main(args: Array <String>) {
    val files = args.map { File(it) }.filter { it.isFile }
    if (files.size != 2) {
        println("Expected exectly 2 files but ${files.size} got:")
        files.forEach { println(it.name) }
    }
    val instructions = readInstructions(files[0])
    println(instructions.size)
    files[1].readLines().filterNot {it == "" }.forEach { processQuery(it, instructions) }
}