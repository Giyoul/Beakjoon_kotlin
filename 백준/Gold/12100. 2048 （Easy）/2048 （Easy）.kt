import java.util.*
import kotlin.math.*

var n = 0
var max = 0L
var directions = listOf(::up, ::right, ::down, ::left)

fun main() = with(System.`in`.bufferedReader()) {
    n = readLine()!!.toInt()
    val arr = Array(n){Array(n){0L}}

    repeat(n){ x ->
        val nums = readLine()!!.split(" ").map { it.toLong() }
        repeat(n) { y -> arr[x][y] = nums[y]}
    }

    dfs(0, arr)
    println(max)
}

fun dfs(depth: Int, arr: Array<Array<Long>>){
    if (depth == 5) {
        repeat(n) { x ->
            repeat(n) { y ->
                max = max(max, arr[x][y])
            }
        }
        return
    }

    for (dir in directions) {
        val tmpArr = arr.map { it.copyOf() }.toTypedArray()
        dir(tmpArr)
        dfs(depth+1, tmpArr)
    }
}

fun up(arr: Array<Array<Long>>){
    val q : Queue<Long> = LinkedList()
    for(i in 0 until n){
        q.clear()

        for(j in 0 until n){
            if(arr[j][i] == 0L) continue
            q.add(arr[j][i])
            arr[j][i] = 0
        }

        var ptr = 0

        while (q.isNotEmpty()) {
            val target = q.poll()
            if(q.isNotEmpty() && q.peek() == target){
                arr[ptr][i] = target * 2
                q.poll()
            } else {
                arr[ptr][i] = target
            }
            ptr++
        }
    }
}

fun down(arr: Array<Array<Long>>){
    val q : Queue<Long> = LinkedList()
    for(i in 0 until n){
        q.clear()

        for(j in n-1 downTo 0){
            if(arr[j][i] == 0L) continue
            q.add(arr[j][i])
            arr[j][i] = 0
        }

        var ptr = n-1

        while (q.isNotEmpty()) {
            val target = q.poll()
            if(q.isNotEmpty() && q.peek() == target){
                arr[ptr][i] = target * 2
                q.poll()
            } else {
                arr[ptr][i] = target
            }
            ptr--
        }
    }
}

fun left(arr: Array<Array<Long>>){
    val q : Queue<Long> = LinkedList()
    for(i in 0 until n){
        q.clear()

        for(j in 0 until n){
            if(arr[i][j] == 0L) continue
            q.add(arr[i][j])
            arr[i][j] = 0
        }

        var ptr = 0

        while (q.isNotEmpty()) {
            val target = q.poll()
            if(q.isNotEmpty() && q.peek() == target){
                arr[i][ptr] = target * 2
                q.poll()
            } else {
                arr[i][ptr] = target
            }
            ptr++
        }
    }
}

fun right(arr: Array<Array<Long>>){
    val q : Queue<Long> = LinkedList()
    for(i in 0 until n){
        q.clear()

        for(j in n-1 downTo 0){
            if(arr[i][j] == 0L) continue
            q.add(arr[i][j])
            arr[i][j] = 0
        }

        var ptr = n-1

        while (q.isNotEmpty()) {
            val target = q.poll()
            if(q.isNotEmpty() && q.peek() == target){
                arr[i][ptr] = target * 2
                q.poll()
            } else {
                arr[i][ptr] = target
            }
            ptr--
        }
    }
}