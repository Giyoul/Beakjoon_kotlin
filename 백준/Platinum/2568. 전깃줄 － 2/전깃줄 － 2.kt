
fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val n = readLine()!!.toInt()
    val edge = Array(n){ Pair(0, 0) }
    val lis = IntArray(n)
    val indexArray = IntArray(n)
    val used = BooleanArray(500001)
    repeat(n) {
        val (from, to) = readLine()!!.split(" ").map { it.toInt() }
        edge[it] = Pair(from, to)
    }

    edge.sortBy { it.first }

    lis[0] = edge[0].second
    indexArray[0] = 0
    var lisIndex = 1

    for(i in 1 until edge.size) {
        if (lis[lisIndex - 1] < edge[i].second) {
            lis[lisIndex++] = edge[i].second
            indexArray[i] = lisIndex - 1
        } else {
            val index = ins(edge[i].second, lis, 0, lisIndex, lisIndex-1)
            indexArray[i] = index
        }
    }
    var length = lisIndex - 1
    for (i in indexArray.size - 1 downTo 0) {
        if (length == indexArray[i]) {
            used[edge[i].first] = true
            length--
        }
    }
    sb.append(n-lisIndex).append("\n")

    for (i in edge) {
        if(used[i.first]) continue
        sb.append(i.first).append("\n")
    }
    println(sb)
}

fun ins(num: Int, dp: IntArray, start: Int, end: Int, lastindex: Int): Int {
    val mid = (start + end) / 2
    if (start >= end) {
        dp[mid] = num
        return mid
    }
    if (dp[mid] > num) {
        return ins(num, dp, start, mid, lastindex)
    } else {
        return ins(num, dp, mid+1, end, lastindex)
    }
}