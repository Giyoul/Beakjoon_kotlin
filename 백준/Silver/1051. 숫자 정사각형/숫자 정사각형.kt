import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()

    val (height, width) = readLine()!!.split(" ").map { it.toInt() }
    val arr = Array(height){ readLine()!!.map { it.toString().toInt() }.toIntArray() }

    val minVal = min(height, width)
    for(i in minVal downTo 2){
        if(checkRect(i-1, width, height, arr)){
            sb.append(i * i)
            break
        }
    }

    if (sb.isEmpty()) sb.append('1')
    println(sb)
}

fun checkRect(length: Int, width: Int, height: Int, arr: Array<IntArray>): Boolean{
    for(i in 0 until height - length){
        for(j in 0 until width - length){
            if(arr[i][j] == arr[i][j+length] && arr[i][j] == arr[i + length][j]
                && arr[i][j] == arr[i + length][j + length]){
                return true
            }
        }
    }
    return false
}