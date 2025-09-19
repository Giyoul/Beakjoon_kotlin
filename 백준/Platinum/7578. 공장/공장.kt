import java.io.StreamTokenizer

lateinit var tree: LongArray

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun StreamTokenizer.nextInt(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = nextInt()
    val arrA = IntArray(n+1) { if (it == 0) 0 else nextInt() }
    val mapB = HashMap<Int, Int>()
    (1..n).forEach{ mapB[nextInt()] = it }

    tree = LongArray(n * 4)
    var ans = 0L

    for (i in 1..n) {
        val valA = arrA[i]
        val valB = mapB[valA]
        ans += sum(1, n, 1, valB!! + 1, n)
        update(1, n, 1, valB, 1)
    }
    println(ans)
}

fun sum(start: Int, end: Int, node: Int, left: Int, right: Int): Long {
    if(end < left || right < start) return 0L
    if(left <= start && end <= right) return tree[node]

    val mid = (start + end) / 2
    return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right)
}

fun update(start: Int, end: Int, node: Int, idx: Int, diff: Int) {
    if(idx < start || end < idx) return

    tree[node] = tree[node] + diff

    if (start != end) {
        val mid = (start + end) / 2
        update(start, mid, node * 2, idx, diff)
        update(mid + 1, end, node * 2 + 1, idx, diff)
    }
}