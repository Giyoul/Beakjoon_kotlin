import java.util.*

data class Jewelry(val weight: Int, val price: Int) : Comparable<Jewelry>{
    override fun compareTo(other: Jewelry): Int {
        return if(this.weight != other.weight) this.weight - other.weight // 무게 다르면 가격 높은것부터 내림차순으로
        else other.price - this.price // 무게 같으면 적은 가격부터 오름차순으로
    }
}

fun main() {
    val br = System.`in`.bufferedReader()

    val (N, K) = br.readLine().split(" ").map { it.toInt() }

    val jewelryPriorityQueue = PriorityQueue<Jewelry>()
    val bag = Array(K){0}

    repeat(N){
        val (m, v) = br.readLine().split(" ").map { it.toInt() }
        jewelryPriorityQueue.add(Jewelry(m, v))
    }

    repeat(K){
        bag[it] = br.readLine().toInt() // 무게 낮은거부터 오름차순
    }

    bag.sort()

    val pricePriorityQueue = PriorityQueue<Int>(Collections.reverseOrder())
    var totalValue = 0L

    bag.forEach {
        while(jewelryPriorityQueue.isNotEmpty()){
            if(jewelryPriorityQueue.peek().weight <= it){
                pricePriorityQueue.add(jewelryPriorityQueue.poll().price)
            } else {
                break;
            }
        }

        if(pricePriorityQueue.isNotEmpty()) totalValue += pricePriorityQueue.poll()
    }

    println(totalValue)
}
