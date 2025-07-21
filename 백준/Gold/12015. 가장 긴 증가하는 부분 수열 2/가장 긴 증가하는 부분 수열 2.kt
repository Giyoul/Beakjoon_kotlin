fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val arr = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    val list = mutableListOf(-1)

    for (item in arr) {
        if(item > list.last()) list.add(item)
        else list[searchLocation(list, item)] = item
    }

    bw.write("${list.size-1}")
    bw.flush()
    bw.close()
}

private fun searchLocation(list: MutableList<Int>, n: Int): Int {
    var low = 0
    var high = list.lastIndex

    while(low <= high) {
        val mid = (low + high) / 2
        when {
            n == list[mid] -> return mid
            n > list[mid] -> low = mid + 1
            else -> high = mid - 1
        }
    }
    return low
}