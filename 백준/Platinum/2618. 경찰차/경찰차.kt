import java.io.StreamTokenizer
import kotlin.math.*


fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val sb = StringBuilder()
    fun StreamTokenizer.nextInt(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = nextInt()
    val w = nextInt()
    val dp = Array(1001){ IntArray(1001) { 0 } }
    val event = Array( 1001){ Pair(0, 0) }
    repeat(w){
        event[it + 1] = Pair(nextInt(), nextInt())
    }

    fun getDistance(type: Boolean, start: Int, end: Int): Int {
        var startEvent = event[start]
        var endEvent = event[end]

        if(start == 0) startEvent = if(type) Pair(1, 1) else Pair(n, n) // 처음 위치 설정

        return abs(startEvent.first - endEvent.first) + abs(startEvent.second - endEvent.second)
    }

    fun dfs(targetEvent:Int, movementOne: Int, movementTwo: Int): Int {
        if(targetEvent > w) return 0

        if(dp[movementOne][movementTwo] != 0) return dp[movementOne][movementTwo]

        val caseOneMove = dfs(targetEvent + 1, targetEvent, movementTwo) + getDistance(true, movementOne, targetEvent)
        val caseTwoMove = dfs(targetEvent + 1, movementOne, targetEvent) + getDistance(false, movementTwo, targetEvent)

        dp[movementOne][movementTwo] = min(caseOneMove, caseTwoMove)
        return dp[movementOne][movementTwo]
    }

    sb.append(dfs(1, 0, 0)).append("\n")

    var oneCount = 0
    var twoCount = 0
    repeat(w) {
        val dist = getDistance(true, oneCount, it+1)
        if (dp[oneCount][twoCount] - dist == dp[it + 1][twoCount]) {
            oneCount = it + 1
            sb.append(1).append("\n")
        } else {
            twoCount = it + 1
            sb.append(2).append("\n")
        }
    }

    println(sb)
}