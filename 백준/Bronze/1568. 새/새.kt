import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun StreamTokenizer.nextInt(): Int{
        nextToken()
        return nval.toInt()
    }

    var n = nextInt()
    var count = 0
    while (n != 0) {
        for(i in 1..n) {
            if (i <= n) {
                n -= i
                count++
            } else break
        }
    }

    println(count)
}