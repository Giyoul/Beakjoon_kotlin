import kotlin.math.roundToLong

lateinit var prime: HashSet<Long>

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine()!!.toLong()

    prime = HashSet()
    var nTmp = n
    var d = 2L
    while (d * d <= nTmp) {
        if (nTmp % d == 0L) {
            prime.add(d)
            while (nTmp % d == 0L) nTmp /= d
        }
        d++
    }
    if (nTmp > 1) {
        prime.add(nTmp)
    }

    println(phi(n))
}

fun phi(n: Long): Long {
    if(n == 1L) return 1

    var result = n.toDouble()
    for (p in prime) {
        result *= (1.0 - 1.0 / p)
    }

    return result.roundToLong()
}