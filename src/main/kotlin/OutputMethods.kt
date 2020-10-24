// Prints one instruction to stdout
fun printInstruction(number: Int, instruction: Instruction) {
    print("$number : ")
    when (instruction) {
        is Instruction.STOP -> println("stop")
        is Instruction.DEC  -> println("dec ${instruction.variableName}")
        is Instruction.INC  -> println("inc ${instruction.variableName}")
        is Instruction.ZERO  -> println("zero ${instruction.variableName} ${instruction.whenTrue} else ${instruction.whenFalse}")
    }
}

// Prints Algorithm to stdout
fun printAlgorithm(algorithm: List<Instruction>) {
    println("Algorithm recognized as:\n")
    algorithm.forEachIndexed { i, it -> if (i > 0) printInstruction(i, it) }
    println("\n----------------------\n")
}