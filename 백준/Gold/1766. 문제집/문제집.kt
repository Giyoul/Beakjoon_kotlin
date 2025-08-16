import java.util.ArrayList
import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.`out`.bufferedWriter()
    val (n, m) = readLine()!!.split(' ').map { it.toInt() }
    val graph = Array(n+1){ArrayList<Int>()}
    val pq = PriorityQueue<Int>()
    val haveToSolveNumber = IntArray(n+1){0}

    repeat(m){
        val (current, next) = readLine()!!.split(' ').map { it.toInt() }
        graph[current].add(next)
        haveToSolveNumber[next]++
    }
    repeat(n) {
        if (haveToSolveNumber[it+1] == 0) pq.add(it+1)
    }

    while (pq.isNotEmpty()) {
        val current = pq.poll()
        bw.write("$current ")
        for(next in graph[current]){
            haveToSolveNumber[next]--
            if(haveToSolveNumber[next] == 0) pq.add(next)
        }
    }
    bw.flush()
    bw.close()
}
