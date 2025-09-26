import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine()!!.toInt()

    val booleanMatrix = (0 until n).map {
        readLine()!!.map { char -> char == 'Y' }
    }

    var max = 0
    for(i in 0 until n) {
        val friendOfFriends = mutableSetOf<Int>()

        val directFriends = (0 until n).filter { booleanMatrix[i][it] }

        directFriends.forEach { j ->
            (0 until n).forEach { k ->
                if(booleanMatrix[j][k] && k != i && !directFriends.contains(k)) friendOfFriends.add(k)
            }
        }
        val totalCount = directFriends.size + friendOfFriends.size
        max = max(max, totalCount)
    }

    println(max)
}