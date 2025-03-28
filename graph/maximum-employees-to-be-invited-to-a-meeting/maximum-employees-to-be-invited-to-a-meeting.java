class Solution {
    public int maximumInvitations(int[] favorite) {
        // a graph w/o pairs; edges representing favorite[i] -> i
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < favorite.length; i++) {
            graph.add(new ArrayList<Integer>());
        }
        // a collection of pairs, i.e. favorite[favorite[i]] = i
        List<List<Integer>> pairs = new ArrayList<List<Integer>>();

        for (int i = 0; i < favorite.length; i++) {    
            if (favorite[favorite[i]] == i) {
                if (i < favorite[i]) {
                    pairs.add(new ArrayList(List.of(i, favorite[i])));
                }
            } else {
                 graph.get(favorite[i]).add(i);
            }
        }

        // 由于每个人都有favorite，该图必定是一个或多个连通图，且每个连通图内必定有一个cycle（边的数量等于点的数量）
        // 又每个人仅有一个favorite，任一节点仅有一个入边，故任意一个连通图内有且仅有一个cycle

        // possible 1 - if there're pairs, accumulate longest paths including each pair 
        int possible1 = 0;
        boolean[] visited = new boolean[favorite.length];
        for (List<Integer> pair : pairs) {
            possible1 += dfs(visited, pair.get(0), graph) + dfs(visited, pair.get(1), graph);
        }

        // possible 2 - find the biggest cycle.
        // 如前所述，任一连通图有且仅有一个cycle，因此该长cycle只能在还没有被visited的图中产生
        int[] paths = new int[favorite.length];
        int[] round = new int[favorite.length];
        int rnd = 1;
        int possible2 = 0;
        for(int i = 0; i < favorite.length; i++) {
            if (visited[i]) { continue; }
            if (paths[i] != 0) { continue; }
            int cycLen = 1;
            int j = i;
            while (paths[j] == 0) {
                paths[j] = cycLen;
                round[j] = rnd;
                j = favorite[j];
                cycLen ++;
            }
            if (round[j] == rnd) {
                possible2 = Math.max(possible2, cycLen - paths[j]);
            }
            rnd++;
        }
        return Math.max(possible1, possible2);

    }

    private int dfs(boolean[] visited, int start, List<List<Integer>> graph) {
        visited[start] = true;
        int maxRes = 0;
        for (int next : graph.get(start)) {
            maxRes = Math.max(maxRes, dfs(visited, next, graph));
        }
        return maxRes + 1;
    }
}