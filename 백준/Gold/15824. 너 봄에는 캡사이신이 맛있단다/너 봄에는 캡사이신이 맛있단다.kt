import java.io.StreamTokenizer

const val MOD = 1000000007

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun StreamTokenizer.nextLong(): Long {
        nextToken()
        return nval.toLong()
    }

    val n = nextLong().toInt()
    val arr = LongArray(n+1)
    val pows = LongArray(n+1)
    pows[0] = 1

    for(i in 1..n) {
        arr[i] = nextLong()
        pows[i] = pows[i-1] * 2 % MOD
    }

    arr.sort()

    var ans = 0L
    for (i in 1..n) {
        ans += (pows[i-1] - pows[n-i]) * arr[i]
        ans %= MOD
    }

    println(ans)
}