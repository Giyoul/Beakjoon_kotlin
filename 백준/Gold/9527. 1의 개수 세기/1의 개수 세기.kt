import kotlin.math.log

val dp = LongArray(55)
fun main() = with(System.`in`.bufferedReader()) {
    var (n, m) = readLine()!!.split(' ').map { it.toLong() }

    dp[0] = 1
    for (i in 1 until 55) dp[i] = (dp[i - 1] shl 1) + (1L shl i)
    println("${calculate(m) - calculate(n-1)}")
}

fun calculate(n: Long) : Long{
    var tmpN = n
    var count = tmpN and 1
    val size = log(tmpN.toDouble(), 2.0)
    for (i in size.toInt() downTo 1) {
        if ((tmpN and (1L shl i)) != 0L) {
            count += dp[i-1] + (tmpN - (1L shl i) + 1)
            tmpN -= (1L shl i)
        }
    }
    return count
}
