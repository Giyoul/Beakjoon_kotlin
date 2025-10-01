import java.math.BigInteger

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    repeat(3) {
        val n = readLine()!!.toInt()
        var ans = BigInteger.ZERO
        repeat(n) {
            ans += readLine()!!.toBigInteger()
        }
        if (ans == BigInteger.ZERO) {
            sb.append("0\n")
        } else if (ans > BigInteger.ZERO) {
            sb.append("+\n")
        } else {
            sb.append("-\n")
        }
    }
    println(sb)
}