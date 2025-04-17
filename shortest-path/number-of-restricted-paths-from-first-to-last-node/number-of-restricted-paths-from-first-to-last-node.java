class Solution {
    public int countRestrictedPaths(int n, int[][] edges) {
        Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            if (adj.get(u) == null) {
                adj.put(u, new HashMap<>());
            }
            if (adj.get(v) == null) {
                adj.put(v, new HashMap<>());
            }
            adj.get(u).put(v, w);
            adj.get(v).put(u, w);
        }

        long[] dist = new long[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[n] = 0;

        int[] pathCounts = new int[n+1];
        pathCounts[n] = 1;

        PriorityQueue<long[]> minHeap = new PriorityQueue<>(n, Comparator.comparingLong(a -> a[0]));
        minHeap.offer(new long[] { 0, n});
        while (!minHeap.isEmpty()) {
            long[] top = minHeap.poll();
            int u = (int) top[1];
            long d = top[0];

            if (adj.get(u) != null) {
                for (Map.Entry<Integer, Integer> edge : adj.get(u).entrySet()) {
                    int v = edge.getKey(), w = edge.getValue();
                    if (dist[v] > d + w) {
                        dist[v] = d + w;
                        minHeap.offer(new long[] { dist[v], v });
                    }
                    if (dist[v] > dist[u]) {
                        pathCounts[v] = (pathCounts[v] + pathCounts[u]) % 1_000_000_007;
                    }
                }
            }
        }
        return pathCounts[1];
    }
}