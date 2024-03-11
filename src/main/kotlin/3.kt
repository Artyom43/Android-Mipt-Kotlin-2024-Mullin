class Tree {

    private var root: Node? = null

    fun insert(value: Int) {
        if (root == null) {
            root = Node(value)
        } else {
            root?.let {
                var currentRoot = it
                while (
                    value < currentRoot.value && currentRoot.left != null ||
                    value >= currentRoot.value && currentRoot.right != null
                ) {
                    currentRoot = if (value < currentRoot.value) {
                        currentRoot.left!!
                    } else {
                        currentRoot.right!!
                    }
                }

                if (value < currentRoot.value) {
                    currentRoot.left = Node(value)
                } else {
                    currentRoot.right = Node(value)
                }
            }
        }
    }

    override fun toString(): String {
        return root?.let {
            preOrderString(it, StringBuilder("")).trim()
        } ?: ""
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
    val tree = Tree()
    repeat(n) {
        tree.insert(readln().trim().toInt())
    }

    println(tree)
}
