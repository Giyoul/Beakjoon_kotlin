import java.io.StreamTokenizer
import java.util.PriorityQueue
import java.util.TreeSet
import kotlin.math.max

data class Node(
    var from: Int,
    var to: Int,
) {
    init {
        if (from > to) {
            from = to.also { to = from }
        }
    }
}

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun StreamTokenizer.nextInt(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = nextInt()
    val list = mutableListOf<Node>()
    repeat(n) {
        list.add(Node(nextInt(), nextInt()))
    }
    list.sortBy { it.to }

    val d = nextInt()

    val pq = PriorityQueue<Node>(compareBy { it.from })
    var ans = 0
    for(node in list) {
        pq.add(node)
        while(pq.isNotEmpty() && pq.peek().from < node.to - d) pq.poll()
        ans = max(ans, pq.size)
    }
    println(ans)
}