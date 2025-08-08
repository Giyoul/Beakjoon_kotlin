fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val mod = 1_000_000_000
    val dp = Array(n + 1) { Array(10) { LongArray(1024) } }

    for (i in 1..9) {
        dp[1][i][1 shl i] = 1
    }

    for (i in 2..n) {
        for (j in 0..9) {
            for (k in 1..1023) {
                val nextBit = k or (1 shl j)
                dp[i][j][nextBit] = when (j) {
                    0 -> dp[i - 1][1][k]
                    9 -> dp[i - 1][8][k]
                    else -> (dp[i - 1][j - 1][k] + dp[i - 1][j + 1][k]) % mod
                }.let { (dp[i][j][nextBit] + it) % mod }
            }
        }
    }

    val result = (0..9).fold(0L) { acc, i ->
        (acc + dp[n][i][1023]) % mod
    }

    println(result)
}