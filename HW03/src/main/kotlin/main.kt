@file: JvmName("main")

fun qsort(arr: List <Int>): List <Int> {
    if (arr.size <= 1)
        return arr
    val x = arr[0]
    val left = qsort(arr.filter {it < x})
    val mid = arr.filter {it == x}
    val right = qsort(arr.filter {it > x})
    return left + mid + right
}

fun main() {
    val inp = readLine()
    val arr : List<Int>
    if (inp == null) {
        println("No input found")
        return
    }
    try {
        arr = inp.split(" ").fold(emptyList<Int>()) {list, str -> list + str.toInt()}
    } catch (e: Exception) {
        println("Incorrect input")
        return
    }
    println(qsort(arr))
}