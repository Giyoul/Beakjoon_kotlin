
lateinit var parent: Array<Int>

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()

    // input
    val (n, m, k) = readLine()!!.split(' ').map { it.toInt() }
    val deck = readLine()!!.split(' ').map { it.toInt() }.toIntArray()
    parent = Array(m+1){ it }

    // 구현부
    deck.sort()
    readLine()!!.split(' ').map {
        var index = binarySearch(deck, it.toInt())
        index = find(index)
        sb.append(deck[index]).append("\n")
        union(index, index + 1)
    }

    print(sb)
}

// 이분 탐색 구현부
fun binarySearch(arr: IntArray, target: Int): Int {
    var left = 0
    var right = arr.size - 1
    while (left <= right) {
        val middle = (left + right) / 2
        if (target >= arr[middle]) {
            left = middle + 1
        } else {
            right = middle - 1
        }
    }
    return left
}

// union-find 알고리즘
fun find(x: Int): Int {
    return when (parent[x]){
        x -> return x
        else -> {
            parent[x] = find(parent[x])
            parent[x]
        }
    }
}

fun union(x: Int, y: Int): Boolean {
    val foundByX = find(x)
    val foundByY = find(y)
    when {
        foundByX == foundByY -> return false
        foundByX > foundByY -> parent[foundByY] = foundByX
        else -> parent[foundByX] = foundByY
    }
    return true
}