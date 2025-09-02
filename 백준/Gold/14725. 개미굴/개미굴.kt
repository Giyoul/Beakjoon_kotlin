import java.io.StreamTokenizer
import java.util.TreeSet

data class Node(
    val feed: String,
    val sibling : TreeSet<Node> = TreeSet()
) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return feed.compareTo(other.feed)
    }
}

val sb = StringBuilder()

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun StreamTokenizer.nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun StreamTokenizer.nextString(): String {
        nextToken()
        return sval
    }

    val n = nextInt()
    val root = Node("root")

    repeat(n) {
        val depth = nextInt()
        var parent = root
        repeat(depth) {
            val feed = nextString()
            val child = parent.sibling.find { it.feed == feed }
            if (child != null) {
                parent = child
            } else {
                val newNode = Node(feed)
                parent.sibling.add(newNode)
                parent = newNode
            }
        }
    }

    dfs(root)
    println(sb)
}

fun dfs(node: Node, depth: Int = -1) {
    if (node.feed != "root") {
        repeat(depth) { sb.append("--") }
        sb.append(node.feed).append("\n")
    }
    for (child in node.sibling) {
        dfs(child, depth + 1)
    }
}