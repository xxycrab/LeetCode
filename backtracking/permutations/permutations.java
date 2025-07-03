
import java.util.Map.Entry;

class Solution {
    // public List<List<Integer>> permute(int[] nums) {
    //     List<List<Integer>> res = new ArrayList<>();
    //     Map<Integer, Boolean> visited = new HashMap<>();
    //     for(int i: nums) {
    //         visited.put(i, false);
    //     }
    //     dfs(res, new ArrayList<>(), visited);
    //     return res;

    // }

    // private void dfs(List<List<Integer>> res, List<Integer> cur, Map<Integer, Boolean> visited) {
    //     if (cur.size() == visited.keySet().size()) {
    //         res.add(new ArrayList<>(cur));
    //         return;
    //     }
    //     for (Entry<Integer, Boolean> entry : visited.entrySet()) {
    //         if (!entry.getValue()) {
    //             cur.add(entry.getKey());
    //             visited.put(entry.getKey(), true);
    //             dfs(res, cur, visited);
    //             visited.put(entry.getKey(), false);
    //             cur.removeLast();
    //         }
    //     }
    // }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }

        for (int i = first; i < n; i++) {
            // 交换first和i的顺序
            Collections.swap(output, first, i);
            // 继续递归从下一个位置进行交换
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }
}
