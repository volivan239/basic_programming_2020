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
    return list.foldRight(emptyList()) {elem, pref -> pref + elem}
}

fun <T> List<T>.filterWithFold(f: (T)->Boolean): List<T> {
    return this.fold(emptyList()) {list, elem -> if (f(elem)) list + elem else list}
}

fun <T> List<T>.filterWithFoldRight(f: (T)->Boolean): List<T> {
    return this.foldRight(emptyList()) {elem, lst -> if (f(elem)) listOf(elem) + lst else lst}
}

fun lengths(strs: List<String>): List<Int> {
    return strs.map { it.length }
}

fun sumsq(n: Int): Int {
    var ans = 0
    (1..n).map { ans += it * it }
    return ans
}

fun <A,B,C> List<B>.mapAccumL(f: (A, B)->Pair<A,C>, init: A): Pair<A, List<C>> {
    val res = Pair(init, emptyList<C>())
    return fold(res) {cur, it -> Pair(f(cur.first, it).first, cur.second + f(cur.first, it).second)}
}

fun main() {
    val inp = readLine()
    val arr : List<Int>
    if (inp == null) {
        println("No input found")
        return
    }
    try {
        arr = inp.split(" ").fold(emptyList()) {list, str -> list + str.toInt()}
    } catch (e: Exception) {
        println("Incorrect input")
        return
    }
    println(qsort(arr))
}