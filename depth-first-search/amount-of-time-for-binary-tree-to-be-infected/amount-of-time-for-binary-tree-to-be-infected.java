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
    public int amountOfTime(TreeNode root, int start) {
        int[] leftTraverse = traverse(root.left, start);
        int[] rightTraverse = traverse(root.right, start);

        int leftHeight = leftTraverse[0];
        int rightHeight = rightTraverse[0];
        System.out.println(rightTraverse[0]);
        System.out.println(rightTraverse[1]);
        if (leftTraverse[1] > 0) {
            return Math.max(leftHeight - leftTraverse[1] + Math.max(rightHeight + 1, leftHeight - leftTraverse[1]), leftTraverse[1] - 1);
        }
        if (rightTraverse[1] > 0) {
            return Math.max(rightHeight - rightTraverse[1] + Math.max(leftHeight + 1, rightHeight - rightTraverse[1]), rightTraverse[1] - 1);
        }
        return Math.max(leftHeight, rightHeight);
    }

    private int[] traverse(TreeNode root, int start) {
        if (root == null) {
            return new int[] { 0, 0 };
        }
        int[] leftTraverse = traverse(root.left, start);
        int[] rightTraverse = traverse(root.right, start);
        int startHeight = 0;
        if (root.val == start) {
            startHeight = 1 + Math.max(leftTraverse[0], rightTraverse[0]);
        } else {
            startHeight = Math.max(leftTraverse[1], rightTraverse[1]);
        }
        int found = leftTraverse[1] > 0 || rightTraverse[1] == 1 ? 1 : 0;
        return new int[] { 1 + Math.max(leftTraverse[0], rightTraverse[0]), startHeight };
    }
}