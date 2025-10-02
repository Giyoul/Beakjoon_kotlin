import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun StreamTokenizer.nextInt(): Int{
       nextToken()
       return nval.toInt()
    }

    val n = nextInt()

    var resY = 0
    var resM = 0

    repeat(n) {
        val input = nextInt()
        val mulY = input / 30 + 1
        val mulM = input / 60 + 1
        resY += 10 * mulY
        resM += 15 * mulM
    }

    if(resM == resY){
        println("Y M $resM")
    } else if (resY < resM) {
        println("Y $resY")
    } else {
        println("M $resM")
    }
}