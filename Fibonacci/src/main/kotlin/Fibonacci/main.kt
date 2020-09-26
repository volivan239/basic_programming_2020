@file: JvmName("main")
package Fibonacci

fun calcFibonacci(n: Int): Int {
    val fib = mutableListOf<Int>(1, 1)
    while (fib.size < n)
        fib.add(fib[fib.size - 1] + fib[fib.size - 2])
    return fib[n - 1]
}

fun main() {
    println("Enter positive integer")
    val s = readLine()
    if (s == null || s.toIntOrNull() == null || s.toInt() <= 0)
        println("$s is not a valid number!")
    else {
        val n = s.toInt()
        println("$n-th Fibonacci number is ${calcFibonacci(n)}")
    }
}