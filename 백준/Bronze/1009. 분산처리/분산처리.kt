import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val sb = StringBuilder()
    fun StreamTokenizer.nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = nextInt()

    repeat(n) {
        val a = nextInt() % 10
        val b = nextInt()

        // a^k의 일의 자리 주기 구하기
        var repeatCount = 0
        var repeatSimulationNum = a
        while (true) {
            repeatCount++
            repeatSimulationNum = (repeatSimulationNum * a) % 10
            if (repeatSimulationNum == a) break
        }

        // b % 주기 == 0 인 경우는 주기의 마지막 원소 선택
        var powCount = b % repeatCount
        if (powCount == 0) powCount = repeatCount

        var ans = 1
        repeat(powCount) {
            ans = (ans * a) % 10
        }

        if (ans == 0) ans = 10
        sb.append(ans).append("\n")
    }

    print(sb)
}