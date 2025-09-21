import java.io.StreamTokenizer
import java.util.PriorityQueue

data class Node(
    val price: Int
): Comparable<Node>{
    override fun compareTo(other: Node): Int {
        return this.price.compareTo(other.price)
    }
}

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun StreamTokenizer.nextInt(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = nextInt()
    val m = nextInt()

    val bundlePq = PriorityQueue<Node>()
    val onePq = PriorityQueue<Node>()

    repeat(m) {
        bundlePq.add(Node(nextInt()))
        onePq.add(Node(nextInt()))
    }

    val lowestBundle = bundlePq.poll().price
    val lowestOne = onePq.poll().price

    if(lowestOne * 6 <= lowestBundle){
        println(lowestOne * n)
    } else {
        val bundleCount = n / 6
        val leftCount = n % 6
        if(leftCount * lowestOne <= lowestBundle){
            println("${lowestBundle * bundleCount + leftCount * lowestOne}")
        } else {
            println("${lowestBundle * (bundleCount + 1)}")
        }
    }
}