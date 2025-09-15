import java.io.StreamTokenizer
import kotlin.math.max
import kotlin.math.min

lateinit var segtree: IntArray
lateinit var inputArr: IntArray
var n = -1

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val sb = StringBuilder()
    fun StreamTokenizer.nextInt(): Int{
        nextToken()
        return nval.toInt()
    }

    fun build(start: Int, end: Int, idx: Int){
        if(start == end){
            segtree[idx] = start
            return
        }
        val mid = (start + end) / 2
        build(start, mid, idx * 2)
        build(mid + 1, end, idx * 2 + 1)

        val leftIdx = segtree[idx * 2]
        val rightIdx = segtree[idx * 2 + 1]
        segtree[idx] = if (inputArr[leftIdx] <= inputArr[rightIdx]) leftIdx else rightIdx
    }

    fun query(start: Int, end: Int, idx: Int, queryStart: Int, queryEnd: Int): Int{
        if(queryStart > end || queryEnd < start) return -1
        if (queryStart <= start && queryEnd >= end) return segtree[idx]

        val mid = (start + end) / 2
        val left = query(start, mid, idx * 2, queryStart, queryEnd)
        val right = query(mid + 1, end, idx * 2 + 1, queryStart, queryEnd)

        if(left == -1) return right
        if(right == -1) return left
        return if(inputArr[left] < inputArr[right]) left else right
    }

    fun maxArea(left: Int, right: Int): Long {
        if (left > right) return 0L
        val minIdx = query(1, n, 1, left, right)
        var area = (right - left + 1).toLong() * inputArr[minIdx]

        val leftArea = maxArea(left, minIdx - 1)
        val rightArea = maxArea(minIdx + 1, right)

        return max(area, max(leftArea, rightArea))
    }

    while (true) {
        n = nextInt()
        if(n == 0) break

        inputArr = IntArray(n + 1) { if(it != 0) nextInt() else 0 }
        segtree = IntArray(n * 4)

        build(1, n, 1)
        sb.append(maxArea(1, n)).append("\n")
    }

    println(sb)
}