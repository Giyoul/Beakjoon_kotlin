import java.io.StreamTokenizer
lateinit var segTree : IntArray
lateinit var arr : IntArray

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val sb = StringBuilder()
    fun StreamTokenizer.nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val testcase = nextInt()

    repeat(testcase){
        var n = nextInt()
        val m = nextInt()
        arr = IntArray(n+1){ if(it == 0) 0 else n - it }
        segTree = IntArray(200000 * 4)

        for(i in 1..n) update(1, 200000, arr[i], 1, 1)
        repeat(m) {
            val idx = nextInt()
            sb.append(query(1, 200000, 1, arr[idx] + 1, 200000)).append(" ")
            update(1, 200000, arr[idx], 0, 1)
            arr[idx] = n++
            update(1, 200000, arr[idx], 1, 1)
        }
        sb.append("\n")
    }
    println(sb)
}

fun query(qstart: Int, qend: Int, idx: Int, start: Int, end: Int): Int{
    if(qend < start || qend > end) return 0
    if(start <= qstart && qend <= end) return segTree[idx]
    val mid = (qstart + qend) / 2
    return query(qstart, mid, idx * 2, start, end) + query(mid + 1, qend, idx * 2 + 1, start, end)
}

fun update(start: Int, end: Int, arrIdx: Int, updateVal: Int, idx: Int): Int{
    if(arrIdx < start || arrIdx > end) return segTree[idx]
    if(start == end) {
        segTree[idx] = updateVal
        return segTree[idx]
    }
    val mid = (start + end) / 2
    segTree[idx] = update(start, mid, arrIdx, updateVal, idx * 2) + update(mid + 1, end, arrIdx, updateVal, idx * 2 + 1)
    return segTree[idx]
}