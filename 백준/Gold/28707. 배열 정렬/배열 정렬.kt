import java.util.PriorityQueue

var n = 0
var m = 0
val map = hashMapOf<Int, Int>()
lateinit var orders : Array<IntArray>
val sb = StringBuilder()

fun main() = with(System.`in`.bufferedReader()) {

    // 초기 값 받기
    n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    sb.append(arr.joinToString(""))
    val input = sb.toString().toInt()
    sb.setLength(0)

    // 이렇게 하면 input 값이 hash의 key 값이 되어서 중복된 케이스는 안들어가게 됨.
    map.put(input, 0)

    // sb를 통해서 최종 목표 만들기
    arr.sort()
    sb.append(arr.joinToString(""))
    val ans = sb.toString().toInt()
    sb.setLength(0)

    // 명령들 입력받기
    m = readLine()!!.toInt()
    orders = Array(m) { IntArray(3) }
    repeat(m) {
        val order = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        orders[it][0] = order[0] - 1
        orders[it][1] = order[1] - 1
        orders[it][2] = order[2]
    }

    // 다익스트라
    val pq = PriorityQueue<IntArray>(compareBy{it[1]})
    pq.add(intArrayOf(input, 0))

    while (pq.isNotEmpty()) {
        val start = pq.poll()

        if (start[0] == ans) break

        repeat(m){
            val next = searchNextNode(start, orders[it])
            val nextNum = next[0]

            if(map.containsKey(nextNum) && map.get(nextNum)!! <= next[1]) return@repeat

            map.put(nextNum, next[1])
            pq.add(next)
        }
    }

    println(map.getOrDefault(ans, -1))
}

fun searchNextNode(start: IntArray, order: IntArray) : IntArray {
    val curNode = start[0] // 노드 번호
    var curNodeCost = start[1] // 현재까지 코스트

    val next = swap(curNode, order) // 스왑 진행
    curNodeCost += order[2]

    return intArrayOf(next, curNodeCost)
}

fun swap(now: Int, order: IntArray): Int {
    val newArray = IntArray(n)
    var nowComp = now
    for(i in n-1 downTo 0) {

        // 얘는 input value 중간에 10이 있는지 검출
        if (nowComp % 10 == 0) {
            newArray[i] = nowComp % 100
            nowComp /= 100
        } else {
            newArray[i] = nowComp % 10
            nowComp /= 10
        }
    }

    // 스왑 진행
    val tmp = newArray[order[0]]
    newArray[order[0]] = newArray[order[1]]
    newArray[order[1]] = tmp

    sb.append(newArray.joinToString(""))
    val returnVal = sb.toString().toInt()
    sb.setLength(0)
    return returnVal
}