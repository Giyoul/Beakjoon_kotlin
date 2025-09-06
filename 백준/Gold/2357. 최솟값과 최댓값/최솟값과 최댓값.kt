import java.io.StreamTokenizer
import kotlin.math.max
import kotlin.math.min

var n = 0
var min = 1000000001
var max = -1
lateinit var elements: IntArray
lateinit var minTree: IntArray
lateinit var maxTree: IntArray

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val sb = StringBuilder()

    fun StreamTokenizer.nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    n = nextInt()
    val m = nextInt()
    elements = IntArray(n+1){ it -> if(it == 0) 0 else nextInt() }

    // segment tree 사이즈
    val treeSize = Math.pow(2.0, Math.ceil(Math.log(n.toDouble()) / Math.log(2.0)) + 1).toInt()

    minTree = IntArray(treeSize)
    maxTree = IntArray(treeSize)
    buildSegmentTree(true, minTree, 1, n, 1)
    buildSegmentTree(false, maxTree, 1, n, 1)

    repeat(m) {
        val a = nextInt()
        val b = nextInt()
        min = 1000000001
        max = -1
        searchMaxMin(true, minTree, 1, n, 1, a, b)
        searchMaxMin(false, maxTree, 1, n, 1, a, b)
        sb.append(min).append(" ").append(max).append("\n")
    }
    println(sb)
}

// type이 true이면 최소트리
fun buildSegmentTree(type: Boolean, tree: IntArray, start: Int, end: Int, node: Int) {
    if (start == end) tree[node] = elements[start]
    else {
        val mid = (start + end) / 2
        buildSegmentTree(type, tree, start, mid, node * 2)
        buildSegmentTree(type, tree, mid + 1, end, node * 2 + 1)

        if (type) {
            tree[node] = if(tree[node * 2] < tree[node * 2 + 1]) tree[node * 2] else tree[node * 2 + 1]
        } else {
            tree[node] = if(tree[node * 2] > tree[node * 2 + 1]) tree[node * 2] else tree[node * 2 + 1]
        }
    }
}

// type true이면 최소
fun searchMaxMin(type: Boolean, tree: IntArray, start: Int, end: Int, node: Int, left: Int, right: Int) {
    if(left > end || right < start) return
    if (left <= start && end <= right) {
        if(type) min = min(min, tree[node])
        else max = max(max, tree[node])
        return
    }

    val mid = (start + end) / 2
    searchMaxMin(type, tree, start, mid, node * 2, left, right)
    searchMaxMin(type, tree, mid + 1, end, node * 2 + 1, left, right)
}
