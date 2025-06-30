func sortedSquares(nums []int) []int {
	var res = make([]int, 0)
	var pivot = -1
	if nums[0] >= 0 {
		pivot = 0
	} else if nums[len(nums)-1] < 0 {
		pivot = len(nums)
	} else {
		for i := 0; i < len(nums)-1; i++ {
			if nums[i] < 0 && nums[i+1] >= 0 {
				pivot = i + 1
				break
			}
		}
	}
	neg := pivot - 1
	pos := pivot
	cur := 0
	for neg >= 0 && pos < len(nums) {
		if nums[neg]*nums[neg] > nums[pos]*nums[pos] {
			cur = nums[pos] * nums[pos]
			pos += 1
		} else {
			cur = nums[neg] * nums[neg]
			neg -= 1
		}
		res = append(res, cur)
	}
	for neg >= 0 {
		cur = nums[neg] * nums[neg]
		res = append(res, cur)
		neg -= 1
	}
	for pos < len(nums) {
		cur = nums[pos] * nums[pos]
		res = append(res, cur)
		pos += 1
	}
	return res
}