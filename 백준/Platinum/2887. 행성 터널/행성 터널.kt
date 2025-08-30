import java.util.PriorityQueue

lateinit var parent: IntArray

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine()!!.toInt()

    parent = IntArray(n + 1)
    repeat(n + 1) { parent[it] = it }

    var X = ArrayList<Pair<Int, Int>>()
    var Y = ArrayList<Pair<Int, Int>>()
    var Z = ArrayList<Pair<Int, Int>>()

    repeat(n) {
        val (x, y, z) = readLine()!!.split(" ").map { it.toInt() }
        X.add(Pair(it, x))
        Y.add(Pair(it, y))
        Z.add(Pair(it, z))
    }
    X.sortBy { it.second }
    Y.sortBy { it.second }
    Z.sortBy { it.second }

    val pq = PriorityQueue<RoadInfo>()
    repeat(n - 1) {
        pq.add(RoadInfo(X[it].first, X[it+1].first, X[it+1].second - X[it].second))
        pq.add(RoadInfo(Y[it].first, Y[it+1].first, Y[it+1].second - Y[it].second))
        pq.add(RoadInfo(Z[it].first, Z[it+1].first, Z[it+1].second - Z[it].second))
    }

    var answer = 0L
    while (pq.isNotEmpty()) {
        val cur = pq.poll()

        if(find(cur.from) != find(cur.to)) {
            union(cur.from, cur.to)
            answer += cur.dist
        }
    }

    println(answer)
}

data class RoadInfo(
    val from: Int,
    val to: Int,
    val dist: Int,
) : Comparable<RoadInfo> {
    override fun compareTo(other: RoadInfo): Int = this.dist - other.dist
}

fun union(from: Int, to: Int) {
    val parentFrom = find(from)
    val parentTo = find(to)

    parent[parentTo] = parentFrom
}

fun find(start: Int): Int {
    if(parent[start] != start) parent[start] = find(parent[start])
    return parent[start]
}