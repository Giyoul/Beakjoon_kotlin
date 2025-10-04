fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()

    var count = 0
    while (true) {
        var (o, w) = readLine()!!.split(' ').map { it.toInt() }

        if(o == 0 && w == 0) break

        count++
        sb.append("$count ")
        val happyFrom = o / 2 + 1
        val happyTo = o * 2 - 1
        var flag = true

        while (true) {
            val (action, value) = readLine()!!.split(' ')

            if (action == "#") break

            if(flag){
                if (action == "F") {
                    w += value.toInt()
                } else {
                    w -= value.toInt()
                }
                if(w <= 0){
                    sb.append("RIP\n")
                    flag = false
                }
            }
        }

        if (flag) {
            if(w in happyFrom..happyTo){
                sb.append(":-)\n")
            } else {
                sb.append(":-(\n")
            }
        }
    }

    println(sb)
}