import Instruction

// Applies instruction and returns true if it was "STOP"
fun go(state: State, instruction: Instruction): Boolean {
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

// Runs algo
fun run(initial: Int, instructions: List<Instruction>): Int {
    val state = State(1, Variables(initial, 0, 0))
    //println("${state.counter}, ${state.variables.x}, ${state.variables.y}")
    while (state.counter < instructions.size && !go(state, instructions[state.counter])) {
        //println("${state.counter}, ${state.variables.x}, ${state.variables.y}")
        if (state.counter <= 0 || state.counter > instructions.size)
            throw Exception("Reference to incorrect pc - ${state.counter}")
    }
    return state.variables.y
}