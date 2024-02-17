fun main() {
    var (a, b) = readln().split(" ").map { it.toLong() }

    while(a != 0L && b != 0L) {
        if (a >= b) {
            a %= b
        } else {
            b %= a
        }
    }

    println(maxOf(a, b))
}