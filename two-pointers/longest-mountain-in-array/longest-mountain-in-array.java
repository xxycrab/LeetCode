class Solution {
    public int longestMountain(int[] arr) {
        if (arr.length < 3)
            return 0;

        int maxLen = 0;
        int start = 0;
        int peak = 0;
        int end = 0;

        while (end < arr.length) {
            while (end < arr.length - 1 && arr[end + 1] <= arr[end])
                end++;
            start = end;
            if (end >= arr.length - 2) {
                break;
            }
            while (end < arr.length - 1 && arr[end + 1] > arr[end])
                end++;
            peak = end;
            if (end >= arr.length - 1)
                break;
            while (end < arr.length - 1 && arr[end + 1] < arr[end])
                end++;

            int curLen = end > peak ? end - start + 1 : 0;
            maxLen = Math.max(maxLen, curLen);
            start = end;
        }

        return maxLen;
    }
}