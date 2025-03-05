/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] res = new int[] { -1, -1 };
        int firstCritical = 0, prevCritical = 0;
        int minDist = Integer.MAX_VALUE, maxDist = 0;
        int index = 0;
        while (head.next != null && head.next.next != null) {
            ListNode cur = head.next;
            ListNode prev = head;
            ListNode next = cur.next;
            index++;

            if (cur.val > prev.val && cur.val > next.val || cur.val < prev.val && cur.val < next.val) {
                if (firstCritical == 0) {
                    firstCritical = index;
                } else {
                    maxDist = index - firstCritical;
                }
                if (prevCritical != 0) {
                    minDist = Math.min(index - prevCritical, minDist);
                }
                prevCritical = index;
            }
            head = head.next;
        }
        if (maxDist != 0) {
            res[0] = minDist;
            res[1] = maxDist;
        }

        return res;

    }
}