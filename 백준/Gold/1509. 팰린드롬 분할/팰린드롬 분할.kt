
var str = ""
lateinit var strArr: CharArray
lateinit var palindromes: Array<BooleanArray>
lateinit var dp: IntArray

fun main() = with(System.`in`.bufferedReader()) {
    str = readLine()
    strArr = str.toCharArray()
    val length = strArr.size
    palindromes = Array(length+1) { BooleanArray(length+1) }
    dp = IntArray(length+1){ 0 }
    initPalindrome(length)
    dpPalindrome(length)
    println(dp[length])
}

fun initPalindrome(length: Int) {
    for (i in 1..length) {
        palindromes[i][i] = true // i부터 i까지가 palindrome 즉, 본인만 가진 것은 팰린드롬이라는 뜻이다.

        if(i < length && strArr[i - 1] == strArr[i]) { // i랑 i-1 즉, 2개만 선택한 것일떄 팰린드롬으로
            palindromes[i][i+1] = true
        }
    }

    for (i in 3..length) { // 길이가 3 이상일 때 / i는 size를 뜻함
        for(j in 1..length - i + 1){ // +i가 오른쪽 끝이니까, size i 만큼 떨어진 곳까지
            val right = j + i - 1
            if(strArr[j-1] == strArr[right-1] && palindromes[j+1][right-1]){ // 양 끝의 수가 서로 같고, 그 안에 있는게 펠린드롬일 경우에
//                println("${j}, ${right}")
                palindromes[j][right] = true
            }
        }
    }
}

fun dpPalindrome(length: Int) {
    for(i in 1..length) {
        for (j in 1..i) {
            if (palindromes[j][i]) {
                if(dp[i] > dp[j-1] + 1 || dp[i] == 0)
                dp[i] = dp[j-1] + 1
            }
        }
    }
}