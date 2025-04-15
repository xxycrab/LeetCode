class Solution {

    private Integer topoOrder;

    public int countPaths(int n, int[][] roads) {
        int[] successors = new int[n];
        int[] distances = new int[n];
        Arrays.fill(successors, -1);
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[0] = 0;

        Map<Integer, Map<Integer,Integer>> adj = new HashMap<>();
        for (int[] road : roads) {
            if (adj.get(road[0]) == null) {
                adj.put(road[0], new HashMap<>());
            }
            if (adj.get(road[1]) == null) {
                adj.put(road[1], new HashMap<>());
            }
            adj.get(road[0]).put(road[1], road[2]);
            adj.get(road[1]).put(road[0], road[2]);
        }


        int[] sorted = topologySort(n, adj);
        

        for (int u : sorted) {
            for (int v : adj.get(u).keySet()) {
                if (distances[v] > distances[u] + adj.get(u).get(v)) {
                    distances[v] = distances[u] +  adj.get(u).get(v);
                    successors[v] = u;
                }
            }
        }

        return sumPaths(0, n-1, distances, adj);
    }

    private int[] topologySort(int n, Map<Integer, Map<Integer,Integer>> adj) {
        int[] visited = new int[n];
        int[] sorted = new int[n];
        this.topoOrder = n;
        for (int i = 0; i < n; i ++) {
            if (visited[i] == 0) {
                dfs(adj, visited, i, sorted);
            }
        }
        return sorted;
    }

    private void dfs(Map<Integer, Map<Integer,Integer>> adj, int[] visited, int u, int[] sorted) {
        visited[u] = 1;
        for (Integer v : adj.get(u).keySet()) {
            if (visited[v] == 0) {
                dfs(adj, visited, v, sorted);
            }
        }
        this.topoOrder = this.topoOrder - 1;
        sorted[this.topoOrder] = u;
        System.out.println(this.topoOrder);
    }

    private int sumPaths(int s, int t, int[] distances, Map<Integer, Map<Integer,Integer>> adj) {
        if (s == t) {
            return 1;
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> edge : adj.get(s).entrySet()) {
            int v = edge.getKey(), weight = edge.getValue();
            if(distances[v] == distances[s] + weight) {
                res += sumPaths(v, t, distances, adj);
            }
        }
        return res;
    }
}