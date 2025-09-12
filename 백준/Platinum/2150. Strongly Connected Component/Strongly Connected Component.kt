import java.io.StreamTokenizer
import java.util.*

lateinit var discovered: IntArray
lateinit var sccId: IntArray
lateinit var list: Array<MutableList<Int>>
lateinit var stack: Stack<Int>
lateinit var result: MutableList<PriorityQueue<Int>>
var idx = 0
var sccCnt = 0

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun StreamTokenizer.nextInt(): Int{
        nextToken()
        return nval.toInt()
    }

    val v = nextInt()
    val e = nextInt()

    list = Array(v + 1) { mutableListOf() }
    discovered = IntArray(v + 1) { -1 }
    sccId = IntArray(v + 1) { -1 }

    repeat(e) {
        val a = nextInt()
        val b = nextInt()
        list[a].add(b)
    }

    result = mutableListOf()
    stack = Stack()

    for (i in 1..v) {
        if (discovered[i] == -1) {
            scc(i)
        }
    }

    println(sccCnt)
    result.sortBy { it.peek() } // 각 SCC의 최소 원소 기준으로 정렬
    val sb = StringBuilder()
    for (q in result) {
        while (q.isNotEmpty()) {
            sb.append("${q.poll()} ")
        }
        sb.append("-1\n")
    }
    print(sb.toString())
}

fun scc(cur: Int): Int {
    discovered[cur] = idx++
    stack.push(cur)

    var ret = discovered[cur]
    for (nxt in list[cur]) {
        if (discovered[nxt] == -1) {
            ret = minOf(ret, scc(nxt))
        } else if (sccId[nxt] == -1) {
            ret = minOf(ret, discovered[nxt])
        }
    }

    if (ret == discovered[cur]) {
        val q = PriorityQueue<Int>()
        while (true) {
            val t = stack.pop()
            q.add(t)
            sccId[t] = sccCnt
            if (t == cur) break
        }
        result.add(q)
        sccCnt++
    }
    return ret
}
