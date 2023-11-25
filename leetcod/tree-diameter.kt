/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    var max = 0
    fun diameterOfBinaryTree(root: TreeNode?): Int {
         dfs(root)
         return max
    }

    fun dfs(root: TreeNode?): Int{
       if(root == null) return 0
       val left = dfs(root?.left) 
       val right =  dfs(root?.right)
        max = maxOf(max , left+right )
       return 1 + maxOf(left,right)
    }
}