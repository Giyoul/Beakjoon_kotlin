
const val MOD = 10007

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine()!!.toInt()

    val comb = Array(53){ IntArray(53) { 0 } }

    repeat(53){ comb[it][0] = 1 }
    for (i in 1..52) {
        for (j in 1..52) {
            comb[i][j] = (comb[i-1][j] + comb[i-1][j-1]) %  MOD
        }
    }

    var ans = 0
    for (i in 1..13) {
        if (n - 4 * i < 0) break

        val term = (comb[52 - 4 * i][n - 4 * i] * comb[13][i]) % MOD
        if (i % 2 == 1) {
            ans = (ans + term) % MOD
        } else {
            ans = (ans - term + MOD) % MOD
        }
    }

    println(ans)
}