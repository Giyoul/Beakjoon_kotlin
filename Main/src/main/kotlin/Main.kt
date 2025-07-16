package com.code

data class Jewelry(val weight: Int, val price: Int) : Comparable<Jewelry>{
    override fun compareTo(other: Jewelry): Int {
        return if(this.weight != other.weight) this.weight - other.weight // 무게 다르면 가격 높은것부터 내림차순으로
        else other.price - this.price // 무게 같으면 적은 가격부터 오름차순으로
    }
}

fun main() {
    println("Hello World!")
}