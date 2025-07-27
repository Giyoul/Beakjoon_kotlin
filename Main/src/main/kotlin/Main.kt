

fun main(){
    val br = System.`in`.bufferedReader()

    var input = br.readLine()!!.split(" ").map { it.toLong() }
    var p1 = Pair(input[0], input[1])
    var p2 = Pair(input[2], input[3])

    input = br.readLine()!!.split(" ").map { it.toLong() }
    var p3 = Pair(input[0], input[1])
    var p4 = Pair(input[2], input[3])

    val p1p2 = ccw(p1, p2, p3) * ccw(p1, p2, p4)
    val p3p4 = ccw(p3, p4, p1) * ccw(p3, p4, p2)

    if(p1p2 == 0 && p3p4 == 0) {
        if(p1LargerThanP2(p1, p2)){
            var tmp = p1
            p1 = p2
            p2 = tmp
        }
        if(p1LargerThanP2(p3, p4)){
            var tmp = p3
            p3 = p4
            p4 = tmp
        }
        if (p1LargerThanP2(p2, p3) && p1LargerThanP2(p4, p1)) println(1) else println(0)
    } else {
        if(p1p2 <= 0 && p3p4 <= 0) println(1) else println(0)
    }
}

fun p1LargerThanP2(p1: Pair<Long, Long>, p2: Pair<Long, Long>) : Boolean {
    if (p1.first == p2.first) {
        return p1.second >= p2.second
    } else {
        return p1.first > p2.first
    }
}

fun ccw(p1: Pair<Long, Long>, p2: Pair<Long, Long>, p3: Pair<Long, Long>): Int{
    val ccw = (p1.first*p2.second + p2.first*p3.second + p3.first*p1.second) - (p2.first*p1.second + p3.first*p2.second + p1.first*p3.second)

    return when{
        ccw < 0 -> -1
        ccw > 0 -> 1
        else -> 0
    }
}