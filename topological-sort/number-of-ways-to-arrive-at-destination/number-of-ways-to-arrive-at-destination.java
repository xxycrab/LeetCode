class Solution {

    private Integer topoOrder;

    public int countPaths(int n, int[][] roads) {
        Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();
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
        int count = 0;

        // This doesn't work. DAG only works when there's no cycle in the group
        // DAG(n, adj, distances, successors);

        int[] pathCounts = new int[n];

        // bellmanFord(n, roads, distances, successors);
        // Arrays.fill(pathCounts, -1);
        // count = sumPaths(0, n - 1, distances, adj, pathCounts);

        pathCounts[0] = 1;
        dijkstra(n, adj, distances, pathCounts);
        count = pathCounts[n-1];

        return count;
    }

    private void dijkstra(int n, Map<Integer, Map<Integer, Integer>> adj, long[] distances, int[] pathCounts) {
        PriorityQueue<long[]> minHeap = new PriorityQueue<>(n, Comparator.comparingLong(a -> a[0]));
        minHeap.offer(new long[]{0, 0});
        while(!minHeap.isEmpty()) {
            long[] top = minHeap.poll();
            int u = (int)top[1];
            long d = top[0];

            for(Map.Entry<Integer, Integer> edge : adj.get(u).entrySet()) {
                int v = edge.getKey(), w = edge.getValue();
                if (d + w < distances[v]) {
                    distances[v] = d + w;
                    minHeap.offer(new long[]{distances[v], v});
                    pathCounts[v] = pathCounts[u];
                } else if (d + w == distances[v]) {
                    if(pathCounts[v] == -1) {
                        pathCounts[v] = 0;
                    }
                    pathCounts[v] = (pathCounts[v] + pathCounts[u]) % 1_000_000_007;
                }
            }
        }

    }

    // DP - find number of pathes from s to t that has the length of distance[t], i.e. cound of shortest paths
    // This DP is correct based on an attribute that if there's a shortest path from s to t, 
    // then for each node v on this path, it's also a shortest path from s to v.
    // This follows the same idea of the solution using dijkstra
    private int sumPaths(int s, int t, long[] distances, Map<Integer, Map<Integer, Integer>> adj, int[] explored) {
        if (s == t) {
            return 1;
        }
        if (explored[s] != -1) {
            return explored[s];
        }
        long res = 0;
        for (Map.Entry<Integer, Integer> edge : adj.get(s).entrySet()) {
            int v = edge.getKey(), weight = edge.getValue();
            if (distances[v] == distances[s] + weight) {
                res += sumPaths(v, t, distances, adj, explored);
                res %= 1_000_000_007;
            }
        }
        explored[s] = (int) res;
        return (int) res;
    }

    private void bellmanFord(int n, int[][] E, long[] distances, long[] successors) {
        for (int i = 0; i < n - 1; i++) {
            for (int[] e : E) {
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

    private void DAG(int n, Map<Integer, Map<Integer, Integer>> adj, long[] distances, long[] successors) {
        int[] sorted = topologySort(n, adj);

        for (int u : sorted) {
            for (int v : adj.get(u).keySet()) {
                if (distances[v] > distances[u] + adj.get(u).get(v)) {
                    distances[v] = distances[u] + adj.get(u).get(v);
                    successors[v] = u;
                }
            }
        }
        for (long u : distances) {
            System.out.println(u);
        }
    }

    private int[] topologySort(int n, Map<Integer, Map<Integer, Integer>> adj) {
        int[] visited = new int[n];
        int[] sorted = new int[n];
        this.topoOrder = n;
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                dfs(adj, visited, i, sorted);
            }
        }
        return sorted;
    }

    private void dfs(Map<Integer, Map<Integer, Integer>> adj, int[] visited, int u, int[] sorted) {
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