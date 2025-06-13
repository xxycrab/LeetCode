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
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        subFlatten(root);
    }

    private TreeNode subFlatten(TreeNode node) {
        if (node.left == null && node.right == null) {
            return node;
        } else {
            if (node.left == null) {
                TreeNode rightTail = subFlatten(node.right);
                return rightTail;
            } else if (node.right == null) {
                TreeNode leftTail = subFlatten(node.left);
                node.right = node.left;
                node.left = null;
                return leftTail;
            } else {
                TreeNode leftTail = subFlatten(node.left);
                TreeNode rightTail = subFlatten(node.right);
                leftTail.right = node.right;
                node.right = node.left;
                node.left = null;
                return rightTail;
            }
        }
    }
}