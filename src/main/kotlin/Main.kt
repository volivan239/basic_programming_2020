import java.io.File
import kotlin.math.*

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
    fun set(variableName: String, newVal: Int): Variables {
        if (newVal < 0) {
            throw Exception("Inccorrect value - $newVal")
        }
        return when (variableName) {
            "x" -> Variables(newVal, y, z)
            "y" -> Variables(x, newVal, z)
            "z" -> Variables(x, y, newVal)
            else -> throw Exception("Incorrect variable - $variableName")
        }
    }

    // Increases $variableName variable by one (command inc in algorithm)
    fun increase(variableName: String) = set(variableName, get(variableName) + 1)

    // Decreases $variableName variable by one (command dec in algorithm)
    fun decrease(variableName: String) = set(variableName, max(get(variableName) - 1, 0))
}


// Stores one instruction (i.e. step / line) of algorithm
sealed class Instruction {
    data class INC(val variableName: String) : Instruction()
    data class DEC(val variableName: String) : Instruction()
    object STOP : Instruction()
    data class ZERO(val variableName: String, val whenTrue: Int, val whenFalse: Int) : Instruction()
}

data class VariablesRange(val minValue: Variables = Variables(), val maxValue: Variables = Variables(), val initialized: Boolean = false) {

    fun increase(variableName: String): VariablesRange {
        require(initialized)
        return VariablesRange(minValue.increase(variableName), maxValue.increase(variableName), true)
    }

    fun decrease(variableName: String): VariablesRange {
        require(initialized)
        return VariablesRange(minValue.decrease(variableName), maxValue.increase(variableName), true)
    }
}

fun variablesRangeUnion(a: VariablesRange, b: VariablesRange): VariablesRange {
    if (!a.initialized)
        return b
    if (!b.initialized)
        return a
    val minValue = Variables(min(a.minValue.x, b.minValue.x), min(a.minValue.y, b.minValue.y), min(a.minValue.z, b.minValue.z))
    val maxValue = Variables(max(a.maxValue.x, b.maxValue.x), max(a.maxValue.y, b.maxValue.y), max(a.maxValue.z, b.maxValue.z))
    return VariablesRange(minValue, maxValue, true)
}

// Stores program state
data class State(val instruction: Instruction, var variablesRange: VariablesRange)

// Runs algorithm on one initial value
fun processQuery(initial: Pair<Int, Int>, algorithm: List<Instruction>) {
    println("For initial value ${initial.first}..${initial.second}:")
    try {
        val (minVal, maxVal) = runAlgorithm(algorithm, initial.first, initial.second)
        println("Result: ${minVal}..${maxVal}\n")
    } catch (e: Exception) {
        println("Run-time error: ${e.message}\n")
    }
}

fun main(args: Array <String>) {
    val files = args.map { File(it) }.filter { it.isFile }
    if (files.size != 2) {
        println("Expected exactly 2 files but ${files.size} got:")
        files.forEach { println(it.name) }
    }
    val algorithm = readAlgorithm(files[0])
    printAlgorithm(algorithm)
    readQueries(files[1]).forEach { processQuery(it, algorithm) }
}