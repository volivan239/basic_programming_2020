// Applies instruction and returns true if it was "STOP"
fun runInstruction(state: State, instruction: Instruction): Boolean {
    return when (instruction) {
        is Instruction.STOP -> true
        is Instruction.DEC  -> {
            state.counter++
            state.variables.decrease(instruction.variableName)
            return false
        }
        is Instruction.INC  -> {
            state.counter++
            state.variables.increase(instruction.variableName)
            return false
        }
        is Instruction.ZERO -> {
            state.counter = if (state.variables.get(instruction.variableName) == 0)
                instruction.whenTrue
            else
                instruction.whenFalse
            return false
        }
    }
}

// Runs algorithm one one initial value
fun runAlgorithm(algorithm: List<Instruction>, initial: Int): Int {
    val state = State(1, Variables(initial, 0, 0))
    while (state.counter < algorithm.size && !runInstruction(state, algorithm[state.counter])) {
        if (state.counter <= 0 || state.counter > algorithm.size)
            throw Exception("Reference to incorrect program counter - ${state.counter}")
    }
    return state.variables.y
}