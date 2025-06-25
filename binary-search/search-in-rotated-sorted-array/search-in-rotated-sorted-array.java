class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        int n = nums.length;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) return mid;
            if(nums[0] <= nums[mid]) {
                if(nums[mid] >= target && nums[0] <= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if(nums[mid] < target && nums[n-1] >= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}