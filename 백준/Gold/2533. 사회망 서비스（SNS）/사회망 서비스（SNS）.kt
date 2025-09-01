import kotlin.math.min

lateinit var graph : Array<ArrayList<Int>>
lateinit var visited : BooleanArray
lateinit var dp : Array<IntArray>

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine()!!.toInt()
    graph = Array(n+1) { ArrayList() }
    visited = BooleanArray(n+1)
    // [n][0]은 n번째 노드를 root 노드로 하는데, root 노드는 얼리아답터가 아닌 경우
    // [n][1]은 n번째 노드를 root 노드로 하는데, root 노드가 얼리아답터인 경우
    dp = Array(n+1) { IntArray(2) { 0 } }

    repeat(n-1) {
        val(from, to) = readLine()!!.split(' ').map { input -> input.toInt() }
        graph[from].add(to)
        graph[to].add(from)
    }

    // 처음에는 root가 무조건 1이 아닐 수도 있는 거로 인지해서, 모든 노드를 root로 두고 반복문을 돌렸는데, 문제의 그래프를 보면 1번이 무조건 root인 것으로 가정하는 것 같다.
    dfs(1) // root 1 일때만 체크 해주면 됨
    println(min(dp[1][0], dp[1][1]))
}

fun dfs(n: Int) {
    visited[n] = true // 이거 안해주면 root 노드로 다시 돌아가서 무한 순환함.
    dp[n][0] = 0
    dp[n][1] = 1

    for (i in graph[n]) {
        if (!visited[i]){
            dfs(i) // 자식 노드 먼저 초기화
            dp[n][0] += dp[i][1]
            dp[n][1] += min(dp[i][0], dp[i][1])
        }
    }
}