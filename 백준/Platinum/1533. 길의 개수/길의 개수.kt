const val MOD = 1_000_003L

fun main() = with(System.`in`.bufferedReader()) {
    val (N, S, E, T) = readLine()!!.split(" ").map { it.toInt() }
    val size = N * 5
    val array = Array(size + 1) { LongArray(size + 1) }

    for (i in 1..N) {
        val str = readLine()!!
        for (j in str.indices) {
            if (str[j] != '0') {
                array[i * 5][(j + 1) * 5 - (str[j] - '0' - 1)] = 1
            }
        }

        for (j in 1 until 5) {
            array[(i - 1) * 5 + j][(i - 1) * 5 + j + 1] = 1
        }
    }

    val result = powProcession(array, T, N)
    println(result[5 * S][5 * E])
}

fun squareProcession(a: Array<LongArray>, b: Array<LongArray>, N: Int = 0): Array<LongArray> {
    val size = if (N != 0) N * 5 else a.size - 1
    val result = Array(size + 1) { LongArray(size + 1) }

    for (i in 1..size) {
        for (j in 1..size) {
            for (k in 1..size) {
                result[i][j] += a[i][k] * b[k][j]
                result[i][j] %= MOD
            }
        }
    }
    return result
}

fun powProcession(a: Array<LongArray>, n: Int, N: Int): Array<LongArray> {
    val size = N * 5
    var res = Array(size + 1) { i -> LongArray(size + 1) { j -> if (i == j) 1L else 0L } }
    var base = a.copyOf()

    var power = n
    while (power != 0) {
        if (power % 2 == 1) res = squareProcession(res, base, N)
        power /= 2
        base = squareProcession(base, base, N)
    }
    return res
}