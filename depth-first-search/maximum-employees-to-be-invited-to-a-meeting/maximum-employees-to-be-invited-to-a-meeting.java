class Solution {
    public int maximumInvitations(int[] favorite) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        List<List<Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < favorite.length; i++) {
            if (favorite[favorite[i]] == i) {
                if (i < favorite[i]) {
                    pairs.add(List.of(i, favorite[i]));
                }
            } else {
                if (graph.get(favorite[i]) == null)
                    graph.put(favorite[i], new ArrayList<>());
                graph.get(favorite[i]).add(i);
            }
        }

        int possible1 = 0;
        boolean[] visited = new boolean[favorite.length];
        for (List<Integer> pair : pairs) {
            possible1 += dfs(graph, pair.get(0), visited) + dfs(graph, pair.get(1), visited);
        }
        System.out.println(possible1);

        int possible2 = 0;
        boolean[] incycle = new boolean[favorite.length];
        for (int i = 0; i < favorite.length; i++) {
            if (visited[i] || incycle[i])
                continue;
            int cycleLen = 0;
            int j = i;
            while (!incycle[j]) {
                incycle[j] = true;
                j = favorite[j];
                cycleLen++;
            }
            possible2 = Math.max(possible2, cycleLen);
            System.out.println(possible2);
        }

        return Math.max(possible1, possible2);

    }

    private int dfs(Map<Integer, List<Integer>> graph, int start, boolean[] visited) {
        visited[start] = true;
        int maxRes = 0;
        if (graph.get(start) != null) {
            for (int i : graph.get(start)) {
                maxRes = Math.max(maxRes, dfs(graph, i, visited));
            }
        }
        return maxRes + 1;
    }
}