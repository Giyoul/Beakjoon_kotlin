fun main() = with(System.`in`.bufferedReader()) {
    val (a, b, c) = readLine()!!.split(" ").map { it.toInt() }

    var maxIdx = 0
    val arr = IntArray(a + b + c + 1){ 0 }
    for(i in 1 .. a){
        for(j in 1 .. b){
            for(k in 1 .. c){
                arr[i + j + k]++
                if(arr[maxIdx] < arr[i + j + k]){
                    maxIdx = i + j + k
                }
            }
        }
    }

    println(maxIdx)
}