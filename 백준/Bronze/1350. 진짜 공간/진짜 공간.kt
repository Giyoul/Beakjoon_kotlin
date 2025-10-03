import java.io.StreamTokenizer
import kotlin.math.ceil

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun StreamTokenizer.nextInt(): Int{
       nextToken()
       return nval.toInt()
    }

    val n = nextInt()
    val files = (0 until n).map { nextInt() }
    val clusterSize = nextInt()

    println(
        LongArray(n) { (ceil((files[it].toDouble() / clusterSize)) * clusterSize.toLong()).toLong() }.sum()
    )
}