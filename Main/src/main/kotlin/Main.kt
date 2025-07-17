import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val arr = Array<LongArray>(4){ LongArray(n) }
    var count = 0L
    val arrEndpointIndex = n*n-1

    repeat(n){ nIter ->
        val st = StringTokenizer(br.readLine())
        repeat(4){ fourIter ->
            arr[fourIter][nIter] = st.nextToken().toLong()
        }
    }

    val arr1 = LongArray(arrEndpointIndex+1)
    val arr2 = LongArray(arrEndpointIndex+1)

    repeat(n){ i ->
        repeat(n){ j ->
            arr1[n*i+j] = arr[0][i] + arr[1][j]
            arr2[n*i+j] = arr[2][i] + arr[3][j]
        }
    }

    arr1.sort()
    arr2.sort()

    var arr1Pointer = 0
    var arr2Pointer = arrEndpointIndex

    while (arr1Pointer <= arrEndpointIndex && arr2Pointer >= 0){
        val sum = arr1[arr1Pointer] + arr2[arr2Pointer]
        if(sum == 0L){
            var arr1SameNumCount = 0;
            var arr2SameNumCount = 0;
            var arr1PointerTemp = arr1Pointer
            var arr2PointerTemp = arr2Pointer

            while(arr1PointerTemp <= arrEndpointIndex && arr1[arr1Pointer] == arr1[arr1PointerTemp++]) arr1SameNumCount++
            while(arr2PointerTemp >= 0 && arr2[arr2Pointer] == arr2[arr2PointerTemp--]) arr2SameNumCount++

            count += arr1SameNumCount * arr2SameNumCount

            arr1Pointer += arr1SameNumCount
            arr2Pointer += arr2SameNumCount
        } else if(sum < 0){
            arr1Pointer++
        } else {
            arr2Pointer--
        }
    }

    println(count)
}