import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val sb = StringBuilder()
    fun StreamTokenizer.nextInt() : Int{
        nextToken()
        return nval.toInt()
    }

    val n = nextInt()
    val inputs = IntArray(n + 1) { if(it != 0) nextInt() else 0 }
    val m = nextInt()
    val segmentTree = SegmentTree(inputs)

    repeat(m) {
        val a = nextInt()
        val b = nextInt()
        val c = nextInt()

        if(a == 1) segmentTree.changeNumInit(b, c)
        else sb.append(segmentTree.queryInit(b, c).index).append("\n")
    }
    println(sb)
}

data class Node(val value: Int, val index: Int)

fun minNode(a: Node, b: Node): Node{
    return if (a.value == b.value) {
        if (a.index < b.index) a else b
    } else {
        if (a.value < b.value) a else b
    }
}

class SegmentTree(
    val input: IntArray,
){
    val size = input.size - 1
    val segmentTree = Array(size * 4){ Node(Int.MAX_VALUE, Int.MAX_VALUE) }
    init {
        build(1, size, 1)
    }

    private fun build(start: Int, end: Int, idx: Int){
        if(start == end) segmentTree[idx] = Node(input[start], start)
        else {
            val mid = (start + end) / 2
            build(start, mid, idx * 2)
            build(mid + 1, end, idx * 2 + 1)
            segmentTree[idx] = minNode(segmentTree[idx * 2], segmentTree[idx * 2 + 1])
        }
    }

    fun changeNumInit(targetIdx: Int, changeValue: Int){
        changeNum(1, size, 1, targetIdx, changeValue)
    }

    private fun changeNum(start: Int, end: Int, idx: Int, targetIdx: Int, changeValue: Int) {
        if(start > targetIdx || end < targetIdx) return
        if(start == end){
            input[targetIdx] = changeValue
            segmentTree[idx] = Node(changeValue, targetIdx)
        } else {
            val mid = (start + end) / 2
            changeNum(start, mid, idx * 2, targetIdx, changeValue)
            changeNum(mid+1, end, idx * 2 + 1, targetIdx, changeValue)
            segmentTree[idx] = minNode(segmentTree[idx * 2], segmentTree[idx * 2 + 1])
        }
    }

    fun queryInit(queryStart: Int, queryEnd: Int): Node {
        return query(1, size, 1, queryStart, queryEnd)
    }

    private fun query(start: Int, end: Int, idx: Int, queryStart: Int, queryEnd: Int): Node {
        if(queryStart > end || queryEnd < start) return Node(Int.MAX_VALUE, Int.MAX_VALUE)
        if(queryStart <= start && end <= queryEnd) return segmentTree[idx]
        val mid = (start + end) / 2
        val left = query(start, mid, idx * 2, queryStart, queryEnd)
        val right = query(mid + 1, end, idx * 2 + 1, queryStart, queryEnd)
        return minNode(left, right)
    }
}