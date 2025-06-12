class Solution {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLen = Math.max(maxLen, 2 * left);
            } else if (left < right) {
                left = right = 0;
            }
        }
        
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLen = Math.max(maxLen, 2 * left);
            } else if (right < left) {
                left = right = 0;
            }
        }

        return maxLen;
    }
    // public int longestValidParentheses(String s) {
    //     Stack<Integer> stack = new Stack<>();
    //     stack.push(-1);
    //     int maxLen = 0;
    //     for(int i = 0; i < s.length(); i++) {
    //         if(s.charAt(i) == '(') {
    //             stack.push(i);
    //         } else {
    //             // top of stack is either index of cloest ( or 
    //             // index of the last ) which results in invalid brackets
    //             // example: (()()))(), when processes the 4th ), it can on longer constructs valid brackets and pushed to stack
    //             // which is used to identify the edge between valid brackets
    //             stack.pop();
    //             if(stack.empty()) {
    //                 stack.push(i);
    //                 continue;
    //             }
    //             maxLen = Math.max(maxLen, i-stack.peek());
    //         }
    //     }
    //     return maxLen;
    // }
}