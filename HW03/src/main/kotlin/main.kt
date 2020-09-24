@file: JvmName("main")

fun qsort(list: List <Int>): List <Int> {
    if (list.size <= 1)
        return list
    val x = list[0]
    val left = qsort(list.filter {it < x})
    val mid = list.filter {it == x}
    val right = qsort(list.filter {it > x})
    return left + mid + right
}

fun reverse(list: List<Int>): List <Int> {
    return list.foldRight(emptyList<Int>()) {elem, lst -> lst + elem}
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