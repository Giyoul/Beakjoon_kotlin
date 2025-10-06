import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun StreamTokenizer.nextInt(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = nextInt()

    var ans = 1
    repeat(n) {
        val n1 = nextInt()
        val n2 = nextInt()

        if(n1 == ans) ans = n2
        else if(n2 == ans) ans = n1
    }

    println(ans)
}