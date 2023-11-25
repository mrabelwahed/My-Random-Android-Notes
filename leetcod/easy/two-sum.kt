class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
      nums.forEachIndexed {
          index, value -> 
            val key = target - value
            if(map.containsKey(key))
              return intArrayOf(index, map[key] ?: 0)
            else
              map[value] = index

      }
      return intArrayOf()
    }
}