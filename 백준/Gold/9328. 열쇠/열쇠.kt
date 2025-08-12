import java.util.*
import kotlin.collections.HashSet

var n = 0
var count = 0
val dx = listOf(0, 0, 1, -1)
val dy = listOf(1, -1, 0, 0)

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    n = readLine()!!.toInt()
    repeat(n) {
        val (height, width) = readLine()!!.split(' ').map { it.toInt() }
        val arr = Array(height+2){
            if(it in 1..height){('.'+readLine().trim()+'.').toCharArray()}
            else{CharArray(width+2){'.'}}
        }
        val hashSet = HashSet<Char>(27)
        hashSet.addAll(readLine().toCharArray().asIterable())
        count = 0
        bfs(arr, hashSet, height, width)
        sb.append(count).append("\n")
    }
    println(sb)
}

fun bfs(arr: Array<CharArray>, hashSet: HashSet<Char>, height: Int, width: Int) {
    val q = LinkedList<Pair<Int, Int>>()
    val visited = Array(height+2){BooleanArray(width+2)}
    q.add(Pair(0,0))
    while (q.isNotEmpty()) {
        val repeatSize = q.size
        var flag = true
        repeat(repeatSize) {
            val (currentY, currentX) = q.poll()
            if(isDoor(arr[currentY][currentX]) && hashSet.contains(arr[currentY][currentX].lowercaseChar()).not()){
                q.add(Pair(currentY, currentX))
                return@repeat
            }
            if(arr[currentY][currentX] == '$') count++
            flag = false
            for (i in 0..3) {
                val ny = currentY + dy[i]
                val nx = currentX + dx[i]
                if (nx < 0 || nx > width + 1 || ny < 0 || ny > height + 1 || visited[ny][nx]) continue
                if (arr[ny][nx] == '$' || arr[ny][nx] == '.') {
                    q.add(Pair(ny, nx))
                    visited[ny][nx] = true
                }
                if (isKey(arr[ny][nx])){
                    hashSet.add(arr[ny][nx])
                    q.add(Pair(ny, nx))
                    visited[ny][nx] = true
                }
                if(isDoor(arr[ny][nx])){
                    q.add(Pair(ny, nx))
                    visited[ny][nx] = true
                }
            }
        }
        if(flag) break
    }
}

fun isKey(target: Char): Boolean = target in 'a'..'z'
fun isDoor(target: Char): Boolean = target in 'A'..'Z'