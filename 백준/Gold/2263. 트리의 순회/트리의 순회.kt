// 히히 내가 이걸 제대로 못풀다니

var n = 0
lateinit var inOrder: IntArray
lateinit var postOrder: IntArray
lateinit var position: IntArray
val bw = System.`out`.bufferedWriter()

fun main() = with(System.`in`.bufferedReader()) {
    n = readLine()!!.toInt()
    inOrder = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    postOrder = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    position = IntArray(n+1){0}

    for(i in 0 until n) {
        position[inOrder[i]] = i
    }

    preOrder(0, n-1, 0, n-1)

    bw.flush()
    bw.close()
}

fun preOrder(inOrderStart: Int, inOrderEnd: Int, postOrderStart: Int, postOrderEnd: Int) {
    if(inOrderStart > inOrderEnd || postOrderStart > postOrderEnd) return

    val parent = postOrder[postOrderEnd]
    bw.write(parent.toString())
    bw.write(" ")

    val leftSize = position[parent] - inOrderStart
    val rightSize = inOrderEnd - position[parent]

    preOrder(inOrderStart, inOrderStart + leftSize - 1, postOrderStart, postOrderStart+leftSize - 1)
    preOrder(inOrderEnd - rightSize + 1, inOrderEnd, postOrderEnd - rightSize, postOrderEnd - 1)
}