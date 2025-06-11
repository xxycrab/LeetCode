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
    public int minCameraCover(TreeNode root) {
        int[] options = dfs(root);
        return options[1];
    }

    // 0 - camera is set on root, the whole tree is monitored
    // 1 - the whole tree is monitored, no matter if the camera is set on root or not
    // 2 - the left and right sub-trees are monitored, no matter if the camera is set on root or not
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[] { Integer.MAX_VALUE/2, 0, 0 };
        }
        int[] leftCam = dfs(root.left);
        int[] rightCam = dfs(root.right);
        
        int[] cam = new int[3];
        cam[0] = leftCam[2] + rightCam[2] + 1;
        cam[1] = Math.min(cam[0], Math.min(leftCam[0] + rightCam[1], leftCam[1] + rightCam[0]));
        cam[2] = Math.min(cam[0], leftCam[1] + rightCam[1]);
        return cam;
    }
}