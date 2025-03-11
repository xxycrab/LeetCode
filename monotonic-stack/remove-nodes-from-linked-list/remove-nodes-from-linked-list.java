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

        Stack<ListNode> prevGreater = new Stack<>();
        
        while(head != null) {
            while(!prevGreater.empty() && prevGreater.peek().val < head.val) {
                prevGreater.pop();
            }
            prevGreater.push(head);
            head = head.next;
        }

        ListNode node = null;
        while(!prevGreater.empty()) {
            ListNode prev = prevGreater.pop();
            prev.next = node;
            node = prev;
        }
        return node;

    }
}