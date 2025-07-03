/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    TreeNode ans;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        inSubTree(root, p, q);
        return ans;
    }

    private boolean inSubTree(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean left = inSubTree(root.left, p, q);
        boolean right = inSubTree(root.right, p, q);
        if ((root == p || root == q) && (left||right) || left&&right) {
            ans = root;
        }
        return root == p || root == q || left||right;
    }
}