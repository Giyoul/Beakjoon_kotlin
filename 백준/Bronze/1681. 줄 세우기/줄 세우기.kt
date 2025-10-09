import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun StreamTokenizer.nextInt(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = nextInt()
    val l = nextInt()

    var count = 0
    var num = 0

    while (count != n){
        num++
        if(!num.toString().contains(l.toString())) count++
    }
    println(num)
}