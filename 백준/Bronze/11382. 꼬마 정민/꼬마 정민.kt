fun main() {
    val br = System.`in`.bufferedReader()
    println(br.readLine().split(" ").map { it.toLong() }.sum())
}