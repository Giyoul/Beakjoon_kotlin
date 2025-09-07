import java.io.StreamTokenizer

var n = 0
var m = 0
var k = 0
lateinit var tree: IntArray
const val MOD = 1000000007L

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val sb = StringBuilder()

    fun StreamTokenizer.nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    n = nextInt()
    m = nextInt()
    k = nextInt()
    tree = IntArray(n){ nextInt() }
    val segment = SegmentTree(tree)

    repeat(k + m) {
        val a = nextInt()
        val b = nextInt()
        val c = nextInt()

        if(a == 1) segment.updateInit(b, c)
        if(a == 2) sb.append(segment.queryInit(b, c) % MOD).append("\n")
    }
    println(sb)
}

class SegmentTree(
    val numbers: IntArray
){
    val size = numbers.size
    val segTree = LongArray(4 * size){ 1L }

    init {
        build(0, size-1, 0)
    }

    private fun build(start: Int, end: Int, idx: Int) {
        if(start == end) segTree[idx] = numbers[start].toLong()
        else{
            val mid = (start + end) / 2
            build(start, mid, 2 * idx + 1)
            build(mid + 1, end, 2 * idx + 2)
            segTree[idx] = (segTree[2 * idx + 1] * segTree[2 * idx + 2]) % MOD
        }
    }

    fun updateInit(b: Int, c: Int) {
        update(0, size-1, 0, b-1, c.toLong())
    }

    private fun update(start: Int, end: Int, idx: Int, updateIdx: Int, updateValue: Long) {
        if(updateIdx < start || updateIdx > end) return
        if (start == end) {
            segTree[idx] = updateValue
            tree[updateIdx] = updateValue.toInt()
        } else {
            val mid = (start + end) / 2
            update(start, mid, 2 * idx + 1, updateIdx, updateValue)
            update(mid+1, end, 2 * idx + 2, updateIdx, updateValue)
            segTree[idx] = segTree[2 * idx + 1] * segTree[2 * idx + 2] % MOD
        }
    }

    fun queryInit(b: Int, c: Int): Long {
        return query(0, size - 1, 0, b - 1, c - 1) % MOD
    }

    private fun query(start: Int, end: Int, Idx: Int, queryStart: Int, queryEnd: Int): Long {
        if(queryStart > end || queryEnd < start) return 1
        if(queryStart <= start && queryEnd >= end) return segTree[Idx]
        val mid = (start + end) / 2
        return query(start, mid, Idx * 2 + 1, queryStart, queryEnd) * query(mid + 1, end, Idx * 2 + 2, queryStart, queryEnd) % MOD
    }
}