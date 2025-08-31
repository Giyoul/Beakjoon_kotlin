
lateinit var parent: IntArray

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine()!!.toInt()
    parent = IntArray(n){ -1 }
    val lines = Array(n){
        val(a, b, c, d) = readLine()!!.split(" ").map { it.toInt() }
        Line(a, b, c, d)
    }

    // 선분 쌍에 대해서 교차 검사 및 그룹 합치기 로직
    for (i in 0 until n) {
        for (j in i + 1 until n) {
            if(lines[i].intersection(lines[j])) union(i, j)
        }
    }

    var count = 0 // 그룹 수
    var min = 0 // 가장 큰 그룸 크기
    repeat(n) {
        if(parent[it] < 0){ // 루트 노드인 경우에
            count++
            if(parent[it] < min) min = parent[it] // 그룹 크기 갱신
        }
    }
    println("${count}\n${-min}")
}

fun union(a: Int, b: Int) {
    val a = find(a)
    val b = find(b)
    if(a == b) return // 이미 유니온

    // 아래부터는 유니온이 아닌 경우임
    val root= if(parent[a] < parent[b]) a else b // 더 큰 집합을 root로 가짐
    val sibling = if(parent[a] < parent[b]) b else a // 작은 집합은 sibling

    parent[root] += parent[sibling] // 새로운 집합의 크기
    parent[sibling] = root // sibling은 더이상 root가 아니므로, union한 친구의 root를 걸어줌.
}

fun find(a: Int): Int {
    if(parent[a] < 0) return a // 초기 상태일 경우
    parent[a] = find(parent[a])
    return parent[a]
}

data class Point(val x: Int, val y: Int) : Comparable<Point> {
    // comparable을 사용해서 각 Point들이 X가 작은 점 -> 큰 점 순으로 오름차순,
    // X가 같다면 Y가 작은 점 -> 큰 점 순으로 오름차순 순으로 정렬해준다.
    // 이는 ccw를 사용할 때, 더 큰 점이 뭔지 한번 더 체크하지 않기 위함임.
    override fun compareTo(other: Point): Int {
        return if(this.x == other.x) this.y - other.y else this.x - other.x
    }
}

class Line(p1: Point, p2: Point) {
    val a: Point
    val b: Point

    constructor(x1: Int, y1: Int, x2: Int, y2: Int) : this(Point(x1, y1), Point(x2, y2))

    init {
        if (p1 <= p2) {
            a = p1
            b = p2
        } else {
            a = p2
            b = p1
        }
    }

    // 선분 교차 판정
    fun intersection(other: Line): Boolean {
        val r1 = ccw(this.a, this.b, other.a) * ccw(this.a, this.b, other.b)
        val r2 = ccw(other.a, other.b, this.a) * ccw(other.a, other.b, this.b)
        if(r1 == 0 && r2 == 0) return (this.a <= other.b) && (other.a <= this.b)
        return r1 <= 0 && r2 <= 0
    }
}

fun ccw(p1: Point, p2: Point, p3: Point): Int {
    val result = (p1.x.toLong() * p2.y + p2.x.toLong() * p3.y + p3.x.toLong() * p1.y) -
            (p2.x.toLong() * p1.y + p3.x.toLong() * p2.y + p1.x.toLong() * p3.y)
    return when {
        result > 0 -> 1
        result < 0 -> -1
        else -> 0
    }
}