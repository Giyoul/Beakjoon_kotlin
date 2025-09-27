
enum class Diraction(val dx: Int, val dy: Int) {
    L(-1, 0),   // 왼쪽
    R(1, 0),    // 오른쪽
    T(0, 1),    // 위
    B(0, -1),   // 아래
    LT(-1, 1),  // 왼쪽 위
    LB(-1, -1), // 왼쪽 아래
    RT(1, 1),   // 오른쪽 위
    RB(1, -1);  // 오른쪽 아래
}

data class Node(
    var dx: Int,
    var dy: Int,
){
    fun splitPos(input: String){
        val posArr = input.toCharArray()
        dx = (posArr[0] - 'A')
        dy = (posArr[1].digitToInt() - 1)
    }

    fun move(dir: Diraction){
        dx += dir.dx
        dy += dir.dy
    }
}

fun moveCheck(dir: Diraction, node: Node): Boolean{
    return !(node.dx + dir.dx < 0 || node.dx + dir.dx > 7 || node.dy + dir.dy < 0 || node.dy + dir.dy > 7)
}

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val (in1, in2, n) = readLine()!!.split(" ")
    val move = n.toInt()
    val kingPos = Node(0, 0)
    kingPos.splitPos(in1)
    val rockPos = Node(0, 0)
    rockPos.splitPos(in2)

    repeat(move){
        val movement = Diraction.valueOf(readLine())
        if (!moveCheck(movement, kingPos)) return@repeat
        else {
            if(kingPos.dx + movement.dx == rockPos.dx && kingPos.dy + movement.dy == rockPos.dy){
                if(!moveCheck(movement, rockPos)) return@repeat
                rockPos.move(movement)
            }
            kingPos.move(movement)
        }
    }

    sb.append("${(kingPos.dx + 'A'.code).toChar()}").append("${kingPos.dy + 1}").append("\n")
    sb.append("${(rockPos.dx + 'A'.code).toChar()}").append("${rockPos.dy + 1}")
    println(sb)
}