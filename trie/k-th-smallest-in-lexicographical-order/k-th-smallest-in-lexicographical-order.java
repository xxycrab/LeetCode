class Solution {
    public int findKthNumber(int n, int k) {
        int cur = 1;
        k--;
        while (k > 0) {
            int numChildren = this.getNumChildren(cur, n);
            if (numChildren <= k) {
                k -= numChildren;
                cur++;
            } else {
                cur *= 10;
                k--;
            }
        }
        return cur;
    }

    // calculate how many children are under this node.
    // We don't really need to construct a Trie as we only care about the kth least number
    // In fact, we can think of it as forward traversing the constructed Trie and find the kth output
    private int getNumChildren(int number, int n) {
        int count = 0;
        long left = number, right = number;
        while (left <= n) {
            count += Math.min(right, n) - left + 1;
            left *= 10;
            right = right * 10 + 9;
        }
        return count;
    }
}

// class Solution {
//     public int findKthNumber(int n, int k) {
//         List<Integer> numbers = new ArrayList<>();
//         for (int i = 1; i < 10 && i <= n; i++) {
//             numbers.add(i);
//             addNumber(i, n, numbers);
//         }
//         return numbers.get(k - 1);
//     }

//     private void addNumber(int cur, int n, List<Integer> numbers) {
//         for (int i = 0; i < 10; i++) {
//             if (cur * 10 + i <= n) {
//                 numbers.add(cur * 10 + i);
//                 cur = cur * 10 + i;
//                 addNumber(cur, n, numbers);
//                 cur /= 10;
//             } else {
//                 return;
//             }
//         }
//     }
// }

// Runtime exceeds
// class Solution {
//     class TrieNode {
//         public TrieNode[] children;
//         public int _value;

//         public TrieNode(int childrenSize) {
//             children = new TrieNode[childrenSize];
//         }

//         public TrieNode addNumber(int number) {
//             TrieNode node = this;
//             List<Integer> digits = new ArrayList<>();
//             int val = number;
//             while (number > 0) {
//                 int d = number % 10;
//                 digits.add(d);
//                 number = number / 10;
//             }
//             for (int i = digits.size() - 1; i >= 0; i--) {
//                 if (node.children[digits.get(i)] == null) {
//                     node.children[digits.get(i)] = new TrieNode(10);
//                 }
//                 node = node.children[digits.get(i)];
//             }
//             node._value = val;
//             return node;
//         }

//         public int getValue() {
//             TrieNode node = this;
//             return node._value;
//         }
//     }

//     public int findKthNumber(int n, int k) {
//         TrieNode root = new TrieNode(10);
//         for (int i = 1; i < 10 && i <= n; i++) {
//             this.addNumbers(i, root, n, k);
//         }
//         List<Integer> sorted = new ArrayList<>();
//         findKth(root, sorted, k);
//         return sorted.get(k);

//     }

//     private void findKth(TrieNode root, List<Integer> sorted, int k) {
//         // System.out.println(root.getValue());
//         sorted.add(root.getValue());
//         for (TrieNode child : root.children) {
//             if (child != null) {
//                 findKth(child, sorted, k);
//             }
//         }
//         if (sorted.size() > k) {
//             return;
//         }
//     }

//     private void addNumbers(int curNumber, TrieNode root, int n, int k) {
//         root.addNumber(curNumber);
//         for (int i = 0; i < 10; i++) {
//             if (curNumber * 10 + i <= n) {
//                 curNumber = curNumber * 10 + i;
//                 addNumbers(curNumber, root, n, k);
//                 curNumber /= 10;
//             } else {
//                 return;
//             }
//         }
//     }
// }
