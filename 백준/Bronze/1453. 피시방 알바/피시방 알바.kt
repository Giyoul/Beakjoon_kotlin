import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun StreamTokenizer.nextInt(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = nextInt()

    val set = HashSet<Int>()

    var count = 0
    repeat(n) {
        val input = nextInt()

        if(set.contains(input)){
            count++
        } else {
            set.add(input)
        }
    }

    println(count)
}