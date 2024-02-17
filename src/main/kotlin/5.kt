fun main() {
    val n = readln().toInt()
    var top: Node? = null

    repeat(n) {
        val command = readln().split(" ")
        when (command[0]) {
            "push" -> {
                top = with(command[1].toInt()) {
                    if (top == null) Node(this, this) else Node(
                        this,
                        maxOf(top!!.currentMax, this),
                        top
                    )
                }
            }

            "pop" -> {
                if (top != null) {
                    top = top!!.next
                }
            }

            "max" -> if (top != null) println(top!!.currentMax)
        }
    }
}

private data class Node(
    val value: Int,
    val currentMax: Int,
    var next: Node? = null
)