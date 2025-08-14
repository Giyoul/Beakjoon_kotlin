import kotlin.math.min

var n = 0
val dp = Array(16){IntArray(1 shl 16){-1}}
val INF = 987654321

fun main() = with(System.`in`.bufferedReader()) {
    n = readLine()!!.toInt()
    val map = Array(n) { readLine()!!.split(' ').map { it.toInt() }.toIntArray()}
    println(dfs(0,1, (1 shl n) - 1, map))
}

fun dfs(node: Int, state: Int, endState: Int, map: Array<IntArray>): Int{
    if(state == endState){
        if(map[node][0] != 0){
            return map[node][0]
        }
        else{
            return INF
        }
    }

    if(dp[node][state] != -1) return dp[node][state]
    dp[node][state] = INF
    for (i in 0 until n) {
        if (state and (1 shl i) != 0 || map[node][i] == 0) continue
        dp[node][state] = min(dp[node][state], map[node][i] + dfs(i, state or (1 shl i), endState, map))
    }
    return dp[node][state]
}