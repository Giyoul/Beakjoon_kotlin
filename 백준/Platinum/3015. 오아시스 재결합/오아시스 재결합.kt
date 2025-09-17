import java.io.StreamTokenizer


fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun StreamTokenizer.nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = nextInt()
    val arr = IntArray(n){ nextInt() }
    var result = 0L
    val stack = ArrayDeque<IntArray>()
    repeat(n) {
        while(stack.isNotEmpty() && stack.first()[0] < arr[it]) result += stack.removeFirst()[1]
        if (stack.isEmpty()) {
            stack.addFirst(intArrayOf(arr[it], 1)) // 0번 인덱스가 숫자고, 1번 인덱스가 개수임
        } else {
            if (stack.first()[0] > arr[it]) {
                stack.addFirst(intArrayOf(arr[it], 1))
                result++
            } else {
                result += stack.first()[1]++
                if(stack.size > 1) result++
            }
        }
    }
    println(result)
}