import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val (y, x) = readLine()!!.split(' ').map { it.toInt() }

    val xCheck = BooleanArray(x){ false }
    val yCheck = BooleanArray(y){ false }

    repeat(y){
        val inputArr = readLine()!!.toCharArray()
        for(i in inputArr.indices){
            if(inputArr[i] == 'X'){
                xCheck[i] = true
                yCheck[it] = true
            }
        }
    }

    var ansX = x
    var ansY = y
    xCheck.forEach { if(it) ansX-- }
    yCheck.forEach { if(it) ansY-- }

    println(max(ansX, ansY))
}