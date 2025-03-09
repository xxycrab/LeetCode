class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashmap = new HashMap<>();
        for(int i = 0; i<nums.length; i++){
            hashmap.put(nums[i], i);
        }
        int[] res = new int[]{};
        for(int i = 0; i<nums.length; i++){
            int complement = target - nums[i];
            if (hashmap.containsKey(complement) && hashmap.get(complement) != i) {
               return new int[] { i, hashmap.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
