import java.io.StreamTokenizer
import java.util.StringTokenizer


fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()

    val (n, m, k) = readLine()!!.split(' ').map { it.toLong() }


    val inputs = LongArray(n.toInt()){ readLine()!!.toLong() }
    val segtree = SegmentTree(inputs)

    repeat((m + k).toInt()){
        val st = StringTokenizer(readLine())
        val a = st.nextToken().toLong()
        val b = st.nextToken().toLong()
        val c = st.nextToken().toLong()

        if (a == 1L) {
            segtree.numChangeInit(b, c)
        }
        if (a == 2L) {
            sb.append(segtree.queryInit(b, c)).append("\n")
        }
    }
    println(sb)
}

class SegmentTree(
    val arr : LongArray
){
    val size = arr.size
    val tree = LongArray(size * 4){ 0L }
    init {
        build(0, size - 1, 0)
    }

    private fun build(start: Int, end: Int, idx: Int){
        if(start == end) tree[idx] = arr[start]
        else{
            val mid = (start + end) / 2
            build(start, mid, idx * 2 + 1)
            build(mid + 1, end, idx * 2 + 2)
            tree[idx] = tree[idx * 2 + 1] + tree[idx * 2 + 2]
        }
    }

    fun numChangeInit(targetIdx: Long, changeValue: Long){
        numChange(0, size - 1, 0, targetIdx-1, changeValue)
    }

    private fun numChange(start:Int, end: Int, idx: Int, targetIdx: Long, changeValue: Long){
        if(start > targetIdx || end < targetIdx) return
        if (start == end) {
            arr[targetIdx.toInt()] = changeValue
            tree[idx] = changeValue
        } else {
            val mid = (start + end) / 2
            numChange(start, mid, idx * 2 + 1, targetIdx, changeValue)
            numChange(mid + 1, end, idx * 2 + 2, targetIdx, changeValue)
            tree[idx] = tree[idx * 2 + 1] + tree[idx * 2 + 2]
        }
    }

    fun queryInit(targetStart: Long, targetEnd: Long): Long{
        return query(0, size-1, 0, targetStart - 1, targetEnd - 1)
    }

    private fun query(start: Int, end: Int, idx: Int, targetStart: Long, targetEnd: Long): Long{
        if(targetStart > end || targetEnd < start) return 0
        if(targetStart <= start && targetEnd >= end) return tree[idx]
        val mid = (start + end) / 2
        return query(start, mid, idx * 2 + 1, targetStart, targetEnd) + query(mid + 1, end, idx * 2 + 2, targetStart, targetEnd)
    }
}