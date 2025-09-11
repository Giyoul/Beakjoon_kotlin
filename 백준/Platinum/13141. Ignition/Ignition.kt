import java.io.StreamTokenizer
import kotlin.math.max
import kotlin.math.min

const val INF = 1_000_000_000_000_000L // 10^15 => 이거 걍 Long.MAX_VALUE로 하면 오버플로우 발생해서 계산값이 갑자기 음수로 나올 수도 있음.

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun StreamTokenizer.nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    fun StreamTokenizer.nextLong(): Long{
        nextToken()
        return nval.toLong()
    }

    val n = nextInt()
    val m = nextInt()

    val dist = Array(n + 1) { LongArray(n + 1) { INF } }
    val start = IntArray(m + 1)
    val end = IntArray(m + 1)
    val length = LongArray(m + 1)

    for(i in 1..n) dist[i][i] = 0 // diagonal을 0으로 변경

    for (i in 1..m) {
        start[i] = nextInt()
        end[i] = nextInt()
        length[i] = nextLong()
        dist[start[i]][end[i]] = min(dist[start[i]][end[i]], length[i])
        dist[end[i]][start[i]] = min(dist[end[i]][start[i]], length[i])
    }

    // 플로이드 와샬
    for (k in 1..n) {
        for (i in 1..n) {
            for (j in 1..n) {
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
            }
        }
    }

    // 가장 긴거 먼저 고르고, 계산은 나중에
    var minLength = INF
    for (i in 1..n) {
        var maxLength = 0L
        for (j in 1..m) {
            maxLength = max(maxLength, dist[i][start[j]] + dist[i][end[j]] + length[j])
        }
        minLength = min(minLength, maxLength)
    }

    // 계산
    println("${minLength / 2}.${if(minLength % 2 == 1L) 5 else 0}")
}
