
fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()

    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(' ').map { it.toInt() }

    val arrWithIndex = arr.mapIndexed { index, value -> Pair(value, index) }.toMutableList()

    arrWithIndex.sortBy { it.first } // value로 sort

    val ansArr = IntArray(n)
    arrWithIndex.forEachIndexed { i, pair ->
        val originIdx = pair.second
        ansArr[originIdx] = i
    }

    println(ansArr.joinToString(" "))
}