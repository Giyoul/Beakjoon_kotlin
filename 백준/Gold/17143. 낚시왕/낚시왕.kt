import java.util.*
import java.util.PriorityQueue

// 위, 아래, 오른쪽, 왼쪽
val dirX = listOf(0, 0, 0, 1, -1)
val dirY = listOf(0, -1, 1, 0, 0)
lateinit var map: Array<HashMap<Int, Shark>>
lateinit var sharks: ArrayDeque<Shark>

fun main() = with(System.`in`.bufferedReader()) {
    val (Y, X, M) = readLine()!!.split(' ').map { it.toInt() }
    map = Array(Y+3){hashMapOf()}
    sharks = ArrayDeque<Shark>()
    val pq = PriorityQueue<SmallShark>()

    repeat(M) {
        val input = readLine()!!.split(' ').map { it.toInt() }.toList()
        val curShark = Shark(input[0], input[1], input[2], input[3], input[4])
        map[input[0]][input[1]] = curShark
        sharks.add(curShark)
        if(curShark.posX == 1) pq.add(SmallShark(curShark.posY, curShark.posX, curShark.size))
    }

    var sumSize = 0

    repeat(X) {
        // 디버깅
//        println(it)
        if (pq.isNotEmpty()){
            // 디버깅
//            println(pq)
            val smallShark = pq.poll()
            sumSize += smallShark.size
            val removedShark = map[smallShark.posY].remove(smallShark.posX)
            sharks.remove(removedShark)
            pq.clear()
        }
        map = Array(Y+3){hashMapOf<Int, Shark>()}
        val repeatSize = sharks.size
        for (i in 0 until repeatSize) {
            val curShark = sharks.removeFirst()

            // 디버깅
//            println(curShark)

            // 이동
            if (curShark.direction == 1 || curShark.direction == 2) {
                val modSpeed = curShark.speed % ((Y - 1) * 2)
                repeat(modSpeed) {
                    if (curShark.posY == 1 && curShark.direction == 1) curShark.direction = 2
                    else if (curShark.posY == Y && curShark.direction == 2) curShark.direction = 1
                    curShark.posY += dirY[curShark.direction]
                }
            } else {
                val modSpeed = curShark.speed % ((X - 1) * 2)
                repeat(modSpeed) {
                    if (curShark.posX == 1 && curShark.direction == 4) curShark.direction = 3
                    else if (curShark.posX == X && curShark.direction == 3) curShark.direction = 4
                    curShark.posX += dirX[curShark.direction]
                }
            }

            // 상어 충돌 처리
            val existShark = map[curShark.posY][curShark.posX]
            if (existShark != null) {
                if (curShark.size > existShark.size) {
                    // 현재 상어가 더 크면 기존 상어 제거
                    sharks.remove(existShark)
                    map[curShark.posY][curShark.posX] = curShark
                    sharks.add(curShark)
                    if (curShark.posX == it + 2) pq.add(SmallShark(curShark.posY, curShark.posX, curShark.size))
                }
                // 기존 상어가 더 크면 현재 상어는 버려짐
            } else {
                // 없으면 그냥 넣기
                map[curShark.posY][curShark.posX] = curShark
                sharks.add(curShark)
                if (curShark.posX == it + 2) pq.add(SmallShark(curShark.posY, curShark.posX, curShark.size))
            }
        }
        // 디버깅
//        println(sumSize)
//        println("-----")
    }

    println(sumSize)
}

data class Shark (
    var posY: Int,
    var posX: Int,
    var speed: Int,
    var direction: Int,
    var size: Int
)

data class SmallShark(
    var posY: Int,
    var posX: Int,
    var size: Int
): Comparable<SmallShark> {
    override fun compareTo(other: SmallShark): Int {
        return if (this.posY != other.posY) {
            this.posY - other.posY
        } else {
            other.size - this.size
        }
    }
}