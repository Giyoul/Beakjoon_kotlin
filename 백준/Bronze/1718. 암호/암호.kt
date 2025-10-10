
fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val charArr = readLine().toCharArray()
    val secret = readLine().toCharArray()

    val secretSize = secret.size
    var pointer = 0
    for(token in charArr) {
        if (token == ' '){
            sb.append(token)
        } else {
//            println("${token.code}, ${secret[pointer].code}")
            var newChar = token.code - secret[pointer].code + 96
//            println(newChar)
            if(newChar < 97) newChar += 26
            sb.append(newChar.toChar())
        }

        pointer++
        if(secretSize == pointer) pointer = 0
    }

    println(sb)
//    println('a'.code)
//    println('z'.code)
//    97 122
}