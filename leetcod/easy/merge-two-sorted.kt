/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    fun mergeTwoLists(_list1: ListNode?, _list2: ListNode?): ListNode? {
        var dummy = ListNode(-1)
        var result = dummy
        var list1 = _list1
        var list2 = _list2
        while(list1!= null && list2 != null){
          if(list1.`val` < list2.`val`){
            result.next = list1
            list1 = list1.next
          }else {
              result.next = list2
              list2 = list2.next
          }
          result = result.next 
        }
      
      if(list1 != null)
       result.next = list1

      if(list2 != null)
       result.next = list2
      
        return dummy.next
    }
}