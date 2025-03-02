class Solution {
    public String kthLargestNumber(String[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private String quickSelect(String[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[right];
        }

        int pivot = partition(nums, left, right);

        int rank = pivot - left + 1;
        if (rank == k) {
            return nums[pivot];
        } else if (rank < k) {
            return quickSelect(nums, pivot + 1, right, k - rank);
        } else {
            return quickSelect(nums, left, pivot - 1, k);
        }
    }

    private int partition(String[] nums, int left, int right) {
        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);
        String tmp = nums[pivotIndex];
        nums[pivotIndex] = nums[right];
        nums[right] = tmp;

        int i = left - 1;
        String pivot = nums[right];
        for (int j = left; j < right; j++) {
            if (compareIntString(nums[j], pivot) > 0) {
                i += 1;
                tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        nums[right] = nums[i + 1];
        nums[i + 1] = pivot;
        return i + 1;
    }

    private int compareIntString(String a, String b) {
        if (a.length() != b.length()) {
            return a.length() - b.length();
        } else {
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    return a.charAt(i) - b.charAt(i);
                }
            }
        }
        return 0;
    }
}