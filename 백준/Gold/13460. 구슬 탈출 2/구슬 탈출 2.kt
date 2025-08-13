import kotlin.collections.ArrayDeque
import kotlin.collections.HashSet

val dx = listOf(0, 0, 1, -1)
val dy = listOf(1, -1, 0, 0)
typealias Pos = Pair<Int, Int>

fun main() = with(System.`in`.bufferedReader()) {
    val (height, width) = readLine()!!.split(' ').map { it.toInt() }
    val map = Array(height){readLine().toCharArray()}
    val dequeue = ArrayDeque<Triple<Pos, Pos, Int>>()
    val visited = HashSet<Pair<Pos, Pos>>()
    var redPos = Pos(0, 0)
    var bluePos = Pos(0, 0)
    var exitPos = Pos(0, 0)
    var ans = -1

    for(i in 0 until height){
        for(j in 0 until width){
            when(map[i][j]){
                'R' -> redPos = Pos(i,j)
                'B' -> bluePos = Pos(i,j)
                'O' -> exitPos = Pos(i,j)
                else -> continue
            }
        }
    }

    map[redPos.first][redPos.second] = '.'
    map[bluePos.first][bluePos.second] = '.'
    dequeue.add(Triple(redPos, bluePos, 0))
    visited.add(Pair(redPos, bluePos))

    while (dequeue.isNotEmpty()) {
        val (red, blue, count) = dequeue.removeFirst()

        if(count == 11) break;
        if (red == exitPos) {
            ans = count
            break
        }

        repeat(4) {
            var redNewY = red.first
            var redNewX = red.second

            while (true) {
                redNewY += dy[it]
                redNewX += dx[it]

                if(map[redNewY][redNewX] == '#'){
                    redNewY -= dy[it]
                    redNewX -= dx[it]
                    break
                }
                if(map[redNewY][redNewX] == 'O') break
            }

            var blueNewY = blue.first
            var blueNewX = blue.second

            while (true) {
                blueNewY += dy[it]
                blueNewX += dx[it]

                if(map[blueNewY][blueNewX] == '#'){
                    blueNewY -= dy[it]
                    blueNewX -= dx[it]
                    break
                }
                if(map[blueNewY][blueNewX] == 'O') return@repeat
            }

            if (redNewX == blueNewX && redNewY == blueNewY) {
                when(it){
                    0 -> if(red.first > blue.first) blueNewY -= dy[it] else redNewY -= dy[it]
                    1 -> if(red.first > blue.first) redNewY -= dy[it] else blueNewY -= dy[it]
                    2 -> if(red.second > blue.second) blueNewX -= dx[it] else redNewX -= dx[it]
                    3 -> if(red.second > blue.second) redNewX -= dx[it] else blueNewX -= dx[it]
                }
            }

            if(!visited.contains(Pair(Pos(redNewY, redNewX), Pos(blueNewY, blueNewX)))){
                visited.add(Pair(Pos(redNewY, redNewX), Pos(blueNewY, blueNewX)))
                dequeue.add(Triple(Pos(redNewY, redNewX), Pos(blueNewY, blueNewX), count+1))
            }
        }

    }

    println(ans)
}
