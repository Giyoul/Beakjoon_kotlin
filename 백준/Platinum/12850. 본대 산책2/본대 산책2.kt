
val map = arrayOf(
    longArrayOf(0, 1, 0, 1, 0, 0, 0, 0),
    longArrayOf(1, 0, 1, 1, 0, 0, 0, 0),
    longArrayOf(0, 1, 0, 1, 1, 1, 0, 0),
    longArrayOf(1, 1, 1, 0, 0, 1, 0, 0),
    longArrayOf(0, 0, 1, 0, 0, 1, 1, 0),
    longArrayOf(0, 0, 1, 1, 1, 0, 0, 1),
    longArrayOf(0, 0, 0, 0, 1, 0, 0, 1),
    longArrayOf(0, 0, 0, 0, 0, 1, 1, 0)
)
const val MOD = 1000000007L

fun main() = with(System.`in`.bufferedReader()) {
    val D = readLine()!!.toInt()
    val ans = customPow(D)
    println(ans[0][0]) // 0이 정보과학관
}

fun customPow(n: Int) : Array<LongArray> {
    if(n == 1) return map
    var tmp = customPow(n / 2)
    tmp = matrixMultiply(tmp, tmp)
    if (n % 2 == 1) { // 홀수개 경우의 수 계산
        tmp = matrixMultiply(tmp, map)
    }
    return tmp
}

// map에서 행렬의 곱을 하면 다른 노드를 거쳐 해당 노드로 가는 경로의 수로 업데이트 된다.
fun matrixMultiply(a: Array<LongArray>, b: Array<LongArray>): Array<LongArray> {
    var tmp = Array(8){LongArray(8)}
    for (i in 0 until 8) {
        for (j in 0 until 8) {
            for (k in 0 until 8) {
                tmp[i][j] = (tmp[i][j] + a[i][k] * b[k][j]) % MOD // 행렬의 곱
            }
        }
    }
    return tmp
}