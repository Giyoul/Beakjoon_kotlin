import java.io.StreamTokenizer
import kotlin.math.ceil
import kotlin.math.log2


fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun StreamTokenizer.nextInt(): Int{
        nextToken()
        return nval.toInt()
    }

    data class Node(val to: Int, val w: Int)

    val n = nextInt()
    val list = Array(n+1){ mutableListOf<Node>() }

    repeat(n - 1) {
        val from = nextInt()
        val to = nextInt()
        val w = nextInt()
        list[from].add(Node(to, w))
        list[to].add(Node(from, w))
    }

    val h = ceil(log2(n.toDouble())).toInt() + 1
    val depth = IntArray(n + 1)
    val dis = IntArray(n + 1)
    val dp = Array(n + 1) { IntArray(h) }

    fun init(cur: Int, d: Int, parent: Int) {
        depth[cur] = d
        for (next in list[cur]) {
            if (next.to != parent) {
                dis[next.to] = dis[cur] + next.w
                init(next.to, d + 1, cur)
                dp[next.to][0] = cur
            }
        }
    }

    fun fillParents(){
        for(i in 1 until h){
            for (j in 1..n) {
                dp[j][i] = dp[dp[j][i - 1]][i - 1]
            }
        }
    }

    fun LCA(a: Int, b: Int): Int {
        var aVar = a
        var bVar = b
        var ah = depth[aVar]
        var bh = depth[bVar]

        if (ah < bh) {
            val tmp = aVar
            aVar = bVar
            bVar = tmp
        }

        for(i in h - 1 downTo 0){
            if((1 shl i) <= depth[aVar] - depth[bVar]){
                aVar = dp[aVar][i]
            }
        }

        if(aVar == bVar) return aVar

        for(i in h-1 downTo 0){
            if (dp[aVar][i] != dp[bVar][i]) {
                aVar = dp[aVar][i]
                bVar = dp[bVar][i]
            }
        }

        return dp[aVar][0]
    }

    init(1, 1, 0)
    fillParents()

    val m = nextInt()
    val sb = StringBuilder()
    repeat(m) {
        val a = nextInt()
        val b = nextInt()
        val res = LCA(a, b)

        sb.append(dis[a] + dis[b] - 2 * dis[res]).append("\n")
    }
    println(sb)
}