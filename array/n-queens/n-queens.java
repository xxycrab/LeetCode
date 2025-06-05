class Solution {
    public List<List<String>> solveNQueens(int n) {

        // diagonais1 stores the places where row-column are same. It can be used to represent diagonais \
        // diagonais2 stores the places where row+column are same. It can be used to represent diagonais /
        Set<Integer> columns = new HashSet<>(), diagonais1 = new HashSet<>(), diagonais2 = new HashSet<>();
        int[] solution = new int[n];
        Arrays.fill(solution, -1);
        List<List<String>> solutions = new ArrayList<>();
        backtrack(solutions, solution, n, 0, columns, diagonais1, diagonais2);
        return solutions;
    }

    private void backtrack(List<List<String>> solutions, int[] curSolution, int n, int index, Set<Integer> columns,
            Set<Integer> diagonais1, Set<Integer> diagonais2) {
        if (index == n) {
            List<String> res = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                char[] queen = new char[n];
                Arrays.fill(queen, '.');
                queen[curSolution[i]] = 'Q';
                res.add(new String(queen));
            }
            solutions.add(res);
            // add new answer
            return;
        }

        for (int i = 0; i < n; i++) {
            if (columns.contains(i) || diagonais1.contains(index - i) || diagonais2.contains(index + i)) {
                continue;
            }
            curSolution[index] = i;
            columns.add(i);
            diagonais1.add(index - i);
            diagonais2.add(index + i);
            backtrack(solutions, curSolution, n, index + 1, columns, diagonais1, diagonais2);
            curSolution[index] = -1;
            columns.remove(i);
            diagonais1.remove(index - i);
            diagonais2.remove(index + i);
        }

    }
}