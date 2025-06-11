/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPath(root);
        return maxSum;
    }

    private int maxPath(TreeNode root) {
        if(root.left == null && root.right == null) {
            this.maxSum = Math.max(root.val, this.maxSum);
            return root.val;
        }
        int leftMaxPath = 0, rightMaxPath = 0;
        if(root.left != null) {
            leftMaxPath = Math.max(maxPath(root.left), 0);
        }
        if(root.right != null) {
            rightMaxPath = Math.max(maxPath(root.right), 0);
        }
        this.maxSum = Math.max(this.maxSum, root.val + leftMaxPath + rightMaxPath);
        return Math.max(leftMaxPath, rightMaxPath) + root.val;
    }
}