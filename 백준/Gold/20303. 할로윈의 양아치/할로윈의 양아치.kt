import java.util.ArrayList
import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val (children, friendRelationship, countToAvoid) = readLine()!!.split(" ").map { it.toInt() }

    val candy = readLine()!!.split(' ').map{ it.toInt() }.toIntArray()
    val relation = Array(children + 1){ ArrayList<Int>() }
    val visited = BooleanArray(children + 1){ false }
    val pq = PriorityQueue<newChild>()

    repeat(friendRelationship) {
        val (from, to) = readLine()!!.split(" ").map { it.toInt() }
        relation[from].add(to)
        relation[to].add(from)
    }

    // 아이들 번호가 1부터 시작한다고 가정하고 루프를 1부터 children까지 돌립니다.
    for(i in 1 .. children){
        if (visited[i]) continue // 이미 방문한 그룹은 건너뛰기

        // BFS를 이용한 그룹 탐색
        var childSum = 0
        var candySum = 0
        val q = ArrayDeque<Int>()
        q.addLast(i)
        visited[i] = true

        while (!q.isEmpty()) {
            val curr = q.removeFirst()
            childSum++
            candySum += candy[curr-1]
            for(neighbor in relation[curr]){
                if (!visited[neighbor]) {
                    visited[neighbor] = true
                    q.addLast(neighbor)
                }
            }
        }
        pq.add(newChild(childSum, candySum))
    }

    val groups = pq.toList().sortedWith(compareBy({ it.childCount }, { -it.candyCount }))

//    for(group in groups){
//        println(group)
//    }

    val dp = IntArray(countToAvoid + 1){ 0 }

    for (group in groups) {
        val currentChildCount = group.childCount
        val currentCandyCount = group.candyCount

        for (j in countToAvoid downTo currentChildCount) {
            dp[j] = maxOf(dp[j], dp[j - currentChildCount] + currentCandyCount)
        }
    }

    println(dp[countToAvoid-1])
}

data class newChild(
    val childCount: Int,
    val candyCount: Int,
) : Comparable<newChild> {
    override fun compareTo(other: newChild): Int {
        if(this.childCount == other.childCount) {
            return other.candyCount - this.candyCount
        }
        return other.childCount - this.childCount
    }
}