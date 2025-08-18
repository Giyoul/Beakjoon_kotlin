
private lateinit var parent: IntArray

fun main() = with(System.`in`.bufferedReader()) {
    val gate = readLine()!!.toInt()
    val plane = readLine()!!.toInt()

    parent = IntArray(gate + 1)
    for (i in 1..gate) parent[i] = i

    var ans = 0

    for (i in 1..plane) {
        val gate = readLine()!!.toInt()
        val docking = find(gate)
        if(docking == 0) break
        ans++
        union(docking, docking-1)
    }
    println(ans)
}

private fun union(x: Int, y: Int) {
    var x = x
    var y = y
    x = find(x)
    y = find(y)
    if (x != y) {
        if(x < y) parent[y] = x else parent[x] = y
    }
}

private fun find(x: Int): Int {
    return if(parent[x] == x) x else find(parent[x]).also { parent[x] = it }
}