class Solution {

    private Integer topoOrder;

    public int countPaths(int n, int[][] roads) {
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


        long[] successors = new long[n];
        long[] distances = new long[n];
        Arrays.fill(successors, -1);
        Arrays.fill(distances, Long.MAX_VALUE);
        distances[0] = 0;
        
        // This doesn't work. DAG only works when there's no cycle in the group
        // DAG(n, adj, distances, successors);

        bellmanFord(n, roads, distances, successors);

        int[] explored = new int[n];
        Arrays.fill(explored, -1);
        return sumPaths(0, n-1, distances, adj, explored);
    }

    private int sumPaths(int s, int t, long[] distances, Map<Integer, Map<Integer,Integer>> adj, int[] explored) {
        if (s == t) {
            return 1;
        }
        if(explored[s] != -1) {
            return explored[s];
        }
        long res = 0;
        for (Map.Entry<Integer, Integer> edge : adj.get(s).entrySet()) {
            int v = edge.getKey(), weight = edge.getValue();
            if(distances[v] == distances[s] + weight) {
                res += sumPaths(v, t, distances, adj, explored);
                 res %= 1_000_000_007;
            }
        }
        explored[s] = (int)res;
        return (int)res;
    }

    private void bellmanFord(int n, int[][] E, long[] distances, long[] successors) {
        for (int i = 0; i < n - 1; i++) {
            for (int[] e: E) {
                int u = e[0], v = e[1], w = e[2];
                if (distances[u] != Long.MAX_VALUE && distances[v] > distances[u] + w) {
                    distances[v] = distances[u] + w;
                    successors[v] = u;
                }
                if (distances[v] != Long.MAX_VALUE && distances[u] > distances[v] + w) {
                    distances[u] = distances[v] + w;
                    successors[u] = v;
                }
            }
        }
    }

    private void DAG(int n,Map<Integer, Map<Integer,Integer>> adj, long[] distances, long[] successors) {
        int[] sorted = topologySort(n, adj);

        for (int u : sorted) {
            for (int v : adj.get(u).keySet()) {
                if (distances[v] > distances[u] + adj.get(u).get(v)) {
                    distances[v] = distances[u] +  adj.get(u).get(v);
                    successors[v] = u;
                }
            }
        }
        for (long u : distances) {
            System.out.println(u);
        }
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
    }
}