
lateinit var map: Array<IntArray>
lateinit var group: Array<IntArray>
lateinit var visited: Array<BooleanArray>
val groupCounter = mutableListOf<Int>()

val dirx = listOf(0, 0, 1, -1)
val diry = listOf(1, -1, 0, 0)

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.`out`.bufferedWriter()

    val (maxHeight, maxWidth) = readLine()!!.split(" ").map { it.toInt() }
    map = Array(maxHeight){ readLine()!!.map { it.digitToInt() }.toIntArray() }
    group = Array(maxHeight){IntArray(maxWidth){0}}
    visited = Array(maxHeight){ BooleanArray(maxWidth){false} }

    var groupNum = 1
    for (height in 0 until maxHeight) {
        for (width in 0 until maxWidth) {
            if (map[height][width] == 1 || group[height][width] > 0) {
                continue
            }

            groupCounter.add(bfs(height, width, maxHeight, maxWidth, groupNum))
            groupNum++
        }
    }

    repeat(maxHeight) { height ->
        repeat(maxWidth) { width ->
            if(map[height][width] == 1){
                var sum = 1
                val hash = HashSet<Int>()
                repeat(4) {
                    val nextHeight = height + diry[it]
                    val nextWidth = width + dirx[it]
                    if(nextWidth in 0 until maxWidth && nextHeight in 0 until  maxHeight){
                        if (group[nextHeight][nextWidth] != 0 && !hash.contains(group[nextHeight][nextWidth])) {
                            sum += groupCounter[group[nextHeight][nextWidth]-1]
                            hash.add(group[nextHeight][nextWidth])
                        }
                    }
                }
                bw.write("${sum%10}")
            } else {
                bw.write("0")
            }
        }
        bw.write("\n")
    }

    bw.flush()
    bw.close()
}

fun bfs(height: Int, width: Int, maxHeight: Int, maxWidth: Int, groupNum: Int): Int{
    val q = ArrayDeque<Pair<Int, Int>>()
    q.addFirst(Pair(height, width))
    var count = 0
    visited[height][width] = true
    while (q.isNotEmpty()) {
        count++
        val (cur_height, cur_width) = q.removeFirst()
        group[cur_height][cur_width] = groupNum
        repeat(4) {
            val nextHeight = cur_height + diry[it]
            val nextWidth = cur_width + dirx[it]
            if((nextWidth in 0 until maxWidth) && (nextHeight in 0 until  maxHeight) && (!visited[nextHeight][nextWidth]) && (map[nextHeight][nextWidth] == 0)){
                q.addFirst(Pair(nextHeight, nextWidth))
                visited[nextHeight][nextWidth] = true
            }
        }
    }

    return count
}
