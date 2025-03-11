/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNodes(ListNode head) {
        if (head.next == null) {
            return head;
        }

        Stack<ListNode> st = new Stack<>();
        
        while(head != null) {
            while(!st.empty() && st.peek().val < head.val) {
                st.pop();
            }
            st.push(head);
            head = head.next;
        }

        ListNode node = null;
        while(!st.empty()) {
            ListNode prev = st.pop();
            prev.next = node;
            node = prev;
        }
        return node;

    }
}