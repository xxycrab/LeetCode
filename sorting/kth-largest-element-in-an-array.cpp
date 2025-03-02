class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
        return quick_select(nums, 0, nums.size()-1, k-1);
    }
    int quick_select(vector<int>& nums, int lo, int hi, int k){
        if(lo==hi) return nums[lo];
        int pivot = (lo+hi)/2;
        pivot = partition(nums,lo,hi,pivot);
        if(k==pivot) return nums[pivot];
        else if(k<pivot)  return quick_select(nums, lo, pivot-1, k);
        else return quick_select(nums, pivot+1, hi, k);
    }
    int partition(vector<int>& nums, int lo, int hi, int pivot){
        int pivotval = nums[pivot];
        swap(nums[hi], nums[pivot]);
        int record = lo;
        for(int i = lo; i<hi; i++){
            if(nums[i]>pivotval) {
                swap(nums[i], nums[record]);
                record++;
            }
        }
        swap(nums[hi], nums[record]);
        return record;
    }
};