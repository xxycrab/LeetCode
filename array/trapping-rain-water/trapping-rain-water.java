class Solution {
    public int trap(int[] height) {
        Stack<List<Integer>> pillars = new Stack<>();
        int rain = 0;

        int start = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] != 0) {
                start = i;
                break;
            }
        }
        if (start == height.length - 1)
            return rain;

        for (int i = start; i < height.length; i++) {
            List<Integer> right = Arrays.asList(i, height[i]);
            if (pillars.empty() || pillars.peek().get(1) >= height[i]) {
                pillars.add(right);
                continue;
            }

            int bottom = pillars.pop().get(1);
            while (!pillars.empty()) {
                List<Integer> left = pillars.peek();
                rain += (Math.min(left.get(1), right.get(1)) - bottom) * (right.get(0) - left.get(0) - 1);
                bottom = Math.min(left.get(1), right.get(1));
                if (left.get(1) <= right.get(1))
                    pillars.pop();
                else
                    break;
            }
            pillars.add(right);
        }

        return rain;
    }
}