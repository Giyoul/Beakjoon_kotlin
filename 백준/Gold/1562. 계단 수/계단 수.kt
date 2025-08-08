

fun main() {
    var br = System.`in`.bufferedReader()
    var bw = System.`out`.bufferedWriter()

    val n = br.readLine().toInt()
    val mod = 1000000000;

    var dp = Array(n+1) {Array(10) {LongArray(1024)}}

    for(i in 1..9){
        dp[1][i][1 shl i] = 1;
    }

    for (i in 2..n) {
        for (j in 0..9) {
            for (k in 1..1023) {
                var nextBit = k or (1 shl j)
                when {
                    j == 0 -> dp[i][j][nextBit] += dp[i-1][1][k]
                    j == 9 -> dp[i][j][nextBit] += dp[i-1][8][k]
                    else -> dp[i][j][nextBit] += (dp[i-1][j-1][k] + dp[i-1][j+1][k]) % mod
                }
                dp[i][j][nextBit] = dp[i][j][nextBit] % mod
            }
        }
    }

    var result = 0L
    for (i in 0..9) {
        result += dp[n][i][1023];
        result %= mod;
    }
    bw.write(result.toString())
    bw.flush()
    bw.close()
    br.close()
}


