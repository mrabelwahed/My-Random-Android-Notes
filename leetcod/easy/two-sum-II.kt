class Solution {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var start = 0
        var end = numbers.size - 1
        while(start <end) {
            if(numbers[start]+ numbers[end] > target)
              end--;
            else if (numbers[start]+ numbers[end] < target)
              start++
            else
             return intArrayOf(++start,++end)  
        }
        return intArrayOf()
    }
}