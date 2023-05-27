/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 *
 */

import java.util.Stack;
fun main() {
   println(primeFactors(2222))
}


fun primeFactors(num: Int){
    var n = num
    if(n>0){
        while(n%2 == 0){
            println("2")
            n /=2;
        }
        
        var i = 3
        while(i<= n){
            if(n % i == 0){
               println(i)
               n /= i
            }
              
            i +=2 
        }
    }
}


fun combV1(){
    for (i in 1 .. 100){
        for(j in 1 .. 100){
             for(k in 1 .. 100){
                if ( Math.pow(i.toDouble(), 2.0) + Math.pow(j.toDouble(),2.0) == Math.pow(k.toDouble(), 2.0))
                    println("i:$i , j:$j , k: $k")
             }
        }
        
    }
}

fun combV2(){
    for (i in 1 .. 100){
        for(j in 1 .. 100){
            val res = (i * i + j * j).toDouble()
            val c = Math.sqrt(res).toInt()
            if ( (i * i + j * j) == (c * c))
                    println("i:$i , j:$j,c: $c")

        }
    }
}

fun calcCheckSum(nums: String): Int{
 
    val chars = nums.toCharArray()
    var count = 1
    var res = 0
    chars.forEach{
        res += it.digitToInt() * count
        count++
    }
  
  return res % 10
    
}

fun calcPrimesTo(n: Int) {
    val primes = BooleanArray(n+1)
     primes.fill(true)
     
    var p = 2
    
    while(p*p <= n){
        if(primes[p]){
            var i = p*p
            while(i<=n){
                primes[i] = false
                i += p
            }
        }
        
        p++
    }
    
    val list = mutableListOf<Int>()
    for(i in 2.. 100){
        if(primes[i])
        list.add(i)
    }
    
    println(list)
}

fun calcPerfectNums(n: Int){
    val list = mutableListOf<Int>()
    for (i in 2 .. n) {
        if(isPerfectNum(i)){
            list.add(i)
        }
    }
    println(list)
}

fun isPerfectNum(n: Int): Boolean {
    val divisors = mutableListOf<Int>()
    for(i in 1 until n){
        if(n%i == 0)
          divisors.add(i)
    }
    return divisors.sum() == n
    
}

fun numberAsText(m: Int): String{
    var n = m
  
    var res = ""
    val stack = Stack<String>()
    
    while(n > 0){
        
       var rem = n % 10
        
        when (rem) {
           
         0 -> stack.push("ZERO")
         1 -> stack.push("ONE")
         2 -> stack.push("TWO")
         3 -> stack.push("THREE")
         4 -> stack.push("FOUR")
         5 -> stack.push("FIVE")
         6 -> stack.push("SIX")
         7 -> stack.push("SEVEN")
         8 -> stack.push("EIGHT")
         9 -> stack.push("NINE")
         
         else -> { throw IllegalArgumentException ("it is not a number ")}
       }
       n = n/10
    }
    
    while(!stack.isEmpty()) {
        val item = stack.pop()
        res += "$item#"
    }
  
    return res.dropLast(1)
}

fun calc (m: Int, n: Int) {
    val res = (m * n)/2
    println(res % 7)
}


fun calcSumAndCountAllNumbersDivBy_2_Or_7(m: Int) {
    val list = mutableListOf<Int>()
    for (i in 1 until m){
        if(i % 2 == 0 || i % 7 == 0)
          list.add(i)
    }
    println("count: ${list.size}, sum: ${list.sum()}")
}

fun isEven(m: Int): Boolean {
    if(m % 2 == 0) return true
    return false
}

fun isOdd(m: Int): Boolean {
    if(m % 2 != 0) return true
    return false
}