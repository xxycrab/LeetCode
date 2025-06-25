class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int[] res = new int[mat.length * mat[0].length];
        int idx = 0;
        int direction = 1;
        for (int i = 0; i <= (mat.length - 1 + mat[0].length - 1); i++) {
            if (direction == 1) {
                int j = Math.min(mat.length - 1, i);
                int k = i-j;
                while(j >= 0 && k < mat[0].length) {
                    res[idx] = mat[j][k];
                    j--;
                    k++;
                    idx++;
                }
            } else {
                int j = Math.max(i-(mat[0].length-1), 0);
                int k = i-j;
                while(j < mat.length && k >=0) {
                    res[idx] = mat[j][k];
                    j++;
                    k--;
                    idx++;
                }
            }
            direction = -direction;
        }
        return res;
    }
}