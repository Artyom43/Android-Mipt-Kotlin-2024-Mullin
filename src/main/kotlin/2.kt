fun main() {
    val (n, m) = readln().split(" ").map { it.toLong() }

    var fPrev = 0L
    var fNext = 1L
    val cached = mutableListOf(fPrev, fNext)

    run lab@{
        (1 until n).forEach {
            val temp = fNext
            fNext = (fNext + fPrev) % m
            fPrev = temp

            if (fPrev == 0L && fNext == 1L) {
                cached.removeLast()
                return@lab
            } else {
                cached.add(fNext)
            }
        }
    }

    println(cached[(n % cached.size).toInt()])
}