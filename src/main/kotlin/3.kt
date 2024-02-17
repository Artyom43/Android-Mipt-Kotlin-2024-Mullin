class Tree(values: List<Int>) {

    private val root: Node

    init {
        root = Node(values.first())
        values.minus(values.first()).forEach {
            var currentRoot = root
            while (
                it < currentRoot.value && currentRoot.left != null ||
                it >= currentRoot.value && currentRoot.right != null
            ) {
                currentRoot = if (it < currentRoot.value) {
                    currentRoot.left!!
                } else {
                    currentRoot.right!!
                }
            }

            if (it < currentRoot.value) {
                currentRoot.left = Node(it)
            } else {
                currentRoot.right = Node(it)
            }
        }
    }

    override fun toString(): String {
        return preOrderString(root, StringBuilder("")).trim()
    }

    private fun preOrderString(root: Node, res: StringBuilder): String {
        res.append("${root.value} ")
        root.left?.let { preOrderString(it, res) }
        root.right?.let { preOrderString(it, res) }
        return res.toString()
    }

    private data class Node(
        val value: Int,
        var left: Node? = null,
        var right: Node? = null
    )
}

fun main() {
    val n = readln().toInt()
    val nums = emptyList<Int>().toMutableList()
    repeat(n) {
        nums.add(readln().toInt())
    }

    println(Tree(nums))
}
