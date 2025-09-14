import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    val sb = StringBuilder()
    val tree = IntArray(1000001 * 4)

    fun StreamTokenizer.nextInt(): Int{
        nextToken()
        return nval.toInt()
    }

    fun update(start: Int, end: Int, idx: Int, taste: Int, count: Int){
        if(start > taste || end < taste) return

        tree[idx] += count // 위에서부터 하단으로 내려감
        if(start == end) return

        val mid = (start + end) / 2
        update(start, mid, idx * 2, taste, count)
        update(mid + 1, end, idx * 2 + 1, taste, count)
    }

    fun query(start: Int, end: Int, idx: Int, target: Int): Int{
        if(start == end){
            update(1, 1000001, 1, start, -1) // 하나 빼주고
            return start
        }

        val mid = (start + end) / 2
        return if(target <= tree[idx * 2]) query(start, mid, idx * 2, target)
        else query(mid + 1, end, idx * 2 + 1, target - tree[idx * 2])
    }

    val n = nextInt()
    repeat(n) {
        val a = nextInt()
        if(a == 1){
            sb.append(query(1, 1000001, 1, nextInt())).append("\n")
        } else {
            update(1, 1000001, 1, nextInt(), nextInt())
        }
    }
    println(sb)
}