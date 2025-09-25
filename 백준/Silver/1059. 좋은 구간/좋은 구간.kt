import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun StreamTokenizer.nextInt(): Int{
        nextToken()
        return nval.toInt()
    }

    val l = nextInt()
    val arr = IntArray(l){ nextInt() }
    val n = nextInt()

    arr.sort()

    println(countBound(arr, n))
}

fun countBound(arr: IntArray, target: Int): Int{
    if(arr.contains(target)) return 0

    val left = arr.lastOrNull{ it < target } ?: 0
    val right = arr.firstOrNull{ it > target } ?: return 0

    return (target - (left + 1) + 1) * ((right - 1) - target + 1) - 1
}

