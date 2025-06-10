class Solution {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length <= 2 || heightMap[0].length <= 2)
            return 0;
        Queue<cube> pq = new PriorityQueue<>((c1, c2) -> (c1.height - c2.height));
        boolean[][] visited = new boolean[heightMap.length][heightMap[0].length];
        for (int i = 0; i < heightMap.length; i++) {
            for (int j = 0; j < heightMap[0].length; j++) {
                if (i == 0 || i == heightMap.length - 1 || j == 0 || j == heightMap[0].length - 1) {
                    pq.add(new cube(i, j, heightMap[i][j]));
                    visited[i][j] = true;
                }
            }
        }
        int rainAmount = 0;
        while (!pq.isEmpty()) {
            cube cur = pq.poll();
            int[][] directions = new int[4][2];
            directions[0] = new int[] { 0, 1 };
            directions[1] = new int[] { 0, -1 };
            directions[2] = new int[] { 1, 0 };
            directions[3] = new int[] { -1, 0 };
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + directions[i][0];
                int ny = cur.y + directions[i][1];
                if (nx >= 0 && nx < heightMap.length && ny >= 0 && ny < heightMap[0].length && !visited[nx][ny]) {
                    rainAmount += cur.height > heightMap[nx][ny] ? cur.height - heightMap[nx][ny] : 0;
                    // This is the key step
                    pq.offer(new cube(nx, ny, Math.max(heightMap[nx][ny], cur.height)));
                    visited[nx][ny] = true;
                }

            }
        }

        return rainAmount;

    }

    private class cube {
        int x;
        int y;
        int height;

        public cube(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
}