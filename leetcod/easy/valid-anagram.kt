class Solution {
    fun isAnagram(s: String, t: String): Boolean {
       if(s.length != t.length) return false

        val arr = IntArray(26)
        s.forEach {
            ch ->
            arr[ch - 'a'] ++
        }

         t.forEach {
            ch ->
            arr[ch - 'a'] --
        }

      arr.forEach {
          value ->
          if (value != 0)
            return false
      }

      return true
    }
}