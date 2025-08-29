import kotlin.math.max

var answer = arrayOf(0, 0)
val left = BooleanArray(20)
val right = BooleanArray(19)

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine()!!.toInt()
    val graph = Array(n){readLine()!!.split(" ").map { it.toInt() }.toIntArray()}
    backTracking(0, 0, n, graph, 0, 0)
    backTracking(0, 1, n, graph, 0, 1)
    println(answer[0] + answer[1])
}

fun backTracking(y: Int, x: Int, n: Int, graph: Array<IntArray>, count: Int, color: Int) {
    var tmpY = y
    var tmpX = x
    if(tmpX >= n){ // 한 줄을 다 확인했으면 다음 줄로 넘어가
        tmpY++
        tmpX = if(tmpX % 2 == 0) 1 else 0
    }
    if (tmpY >= n) {
        answer[color] = max(answer[color], count)
        return
    }

    if(graph[tmpY][tmpX] == 1 && !left[tmpX - tmpY + n] && !right[tmpY + tmpX]){
        left[tmpX - tmpY + n] = true
        right[tmpY + tmpX] = true
        backTracking(tmpY, tmpX+2, n, graph, count+1, color)
        left[tmpX - tmpY + n] = false
        right[tmpY + tmpX] = false
    }
    backTracking(tmpY, tmpX+2, n, graph, count, color)
}