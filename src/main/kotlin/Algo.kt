import kotlin.math.*

// Relaxes value of askedState
fun relax(askedState: Int, states: MutableList<State>, newVariablesRange: VariablesRange, unrelaxed: MutableSet<Int>) {
    if (askedState < 0 || askedState > states.size)
        throw Exception("Not a correct state - $askedState")
    val state = if (askedState != states.size)
        askedState
    else
        0
    val union = variablesRangeUnion(states[state].variablesRange, newVariablesRange)
    if (union != states[state].variablesRange && state != 0)
        unrelaxed.add(state)
    states[state].variablesRange = union
}

// Applies instruction
fun applyInstruction(state: Int, states: MutableList<State>, unrelaxed: MutableSet<Int>) {
    unrelaxed.remove(state)
    val instruction = states[state].instruction
    val variablesRange = states[state].variablesRange
    //println("state $state: x from ${variablesRange.minValue.x} to ${variablesRange.maxValue.x}")
    //println("state $state: y from ${variablesRange.minValue.y} to ${variablesRange.maxValue.y}")
    when (instruction) {
        is Instruction.STOP -> {
            states[0].variablesRange = variablesRangeUnion(states[0].variablesRange, variablesRange)
        }
        is Instruction.DEC  -> {
            relax(state + 1, states, variablesRange.decrease(instruction.variableName), unrelaxed)
        }
        is Instruction.INC  -> {
            relax(state + 1, states, variablesRange.increase(instruction.variableName), unrelaxed)
        }
        is Instruction.ZERO -> {
            if (variablesRange.minValue.get(instruction.variableName) == 0) {
                val newMaxValue = variablesRange.maxValue.set(instruction.variableName, 0)
                val newVariablesRange = VariablesRange(variablesRange.minValue, newMaxValue, true)
                relax(instruction.whenTrue, states, newVariablesRange, unrelaxed)
            }
            if (variablesRange.maxValue.get(instruction.variableName) != 0) {
                val newMinValue = variablesRange.minValue.set(instruction.variableName, max(variablesRange.minValue.get(instruction.variableName), 1))
                val newVariablesRange = VariablesRange(newMinValue, variablesRange.maxValue, true)
                relax(instruction.whenFalse, states, newVariablesRange, unrelaxed)
            }
        }
    }
}

// Runs algorithm one one initial range
// 0-th value of states means final state
fun runAlgorithm(algorithm: List<Instruction>, initialMin: Int, initialMax: Int): Pair<Int, Int> {
    val states = algorithm.map { State(it, VariablesRange()) }.toMutableList()
    states[1].variablesRange = VariablesRange(Variables(initialMin, 0, 0), Variables(initialMax, 0, 0), true)
    val unrelaxed = mutableSetOf(1)
    while (unrelaxed.isNotEmpty())
        applyInstruction(unrelaxed.random(), states, unrelaxed)
    return Pair(states[0].variablesRange.minValue.y, states[0].variablesRange.maxValue.y)
}