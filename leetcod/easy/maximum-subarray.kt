class Solution {
    fun maxSubArray(nums: IntArray): Int {
       if(nums.size == 1) return nums[0]

        var sum = 0
        var max = Int.MIN_VALUE

        nums.forEachIndexed {
            index, value -> 
            sum += nums[index] 
            max = Math.max(max, sum)
           if(sum < 0) sum = 0
            
        }
        return max 
    }
}