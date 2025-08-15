import java.io.BufferedWriter


fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.`out`.bufferedWriter()
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }
    val list = mutableListOf<Int>()
    val indexDp = IntArray(n)

    list.add(Int.MIN_VALUE)
    for (i in 0 until n) {
        if(arr[i] > list[list.size - 1]) {
            list.add(arr[i])
            indexDp[i] = list.size - 1
        } else {
            var left = 0
            var right = list.size - 1
            while (left < right) {
                var center = (left + right) / 2
                if(list[center] < arr[i]){
                    left = center + 1
                } else {
                    right = center
                }
            }
            list[right] = arr[i]
//            indexDp[i] = indexDp[right]
            indexDp[i] = right
        }
    }

    bw.write("${list.size - 1}\n")
    val deq = ArrayDeque<Int>()
    var count = list.size-1
    for (i in n-1 downTo 0) {
        if(count == 0) break
        if (indexDp[i] == count) {
            deq.addFirst(arr[i])
            count--
        }
    }

    while (deq.isNotEmpty()) {
        bw.write("${deq.removeFirst()} ")
    }
    bw.flush()
    bw.close()
}
