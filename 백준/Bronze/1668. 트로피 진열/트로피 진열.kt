import java.io.StreamTokenizer
import kotlin.math.max

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val sb = StringBuilder()
    fun StreamTokenizer.nextInt(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = nextInt()

    val arr = List(n) { nextInt() }

    fun calculate(arr: List<Int>): Int {
        var visibleCount = 0
        var maxHeight = 0
        for (i in arr) {
            if (i > maxHeight) {
                visibleCount++
                maxHeight = i
            }
        }
        return visibleCount
    }

    val forwardAns = calculate(arr)

    val backwardAns = calculate(arr.reversed())

    sb.append(forwardAns).append("\n").append(backwardAns)

    println(sb)
}