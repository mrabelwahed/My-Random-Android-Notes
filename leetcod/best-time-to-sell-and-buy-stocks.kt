class Solution {
    fun maxProfit(prices: IntArray): Int {
       
      var max = 0
      var min = prices[0]
      if(prices.isEmpty()) return max

      for(i in prices){
          if(i < min)
              min = i
          max = Math.max(max, i - min)
      }


     return max
    }
}