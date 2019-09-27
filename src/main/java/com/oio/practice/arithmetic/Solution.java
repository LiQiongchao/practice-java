package com.oio.practice.arithmetic;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class AddMatch {

    /*
    // 判断太多，会导致时间超时，增加时间复杂度。
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
          ListNode head = new ListNode(0), ind;
        ind = head;
        int i1, i2, add = 0;
        i1 = i2 = 0;
        while (l1 != null || l2 != null) {
            int i3 = int i3 = (l1 == null? 0:l1.val) + (l2 == null? 0:l2.val);
            if (add == 1) {
                i3 += 1;
                add = 0;
            }
            if (i3 >= 10) {
                add = 1;
                i3 = i3 % 10;
            }
            ind.next = new ListNode(i3);
            ind = ind.next;
        }
        if (add == 1) {
            ind.next = new ListNode(add);
        }
        return head.next;
    }
     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0), ind;
        ind = head;
        int add = 0;
        while (l1 != null || l2 != null) {
            int i3 = (l1 == null? 0:l1.val) + (l2 == null? 0:l2.val) + add;
            add = i3/10;
            ind.next = new ListNode(i3 % 10);
            ind = ind.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (add >= 1) {
            ind.next = new ListNode(add);
        }
        return head.next;
    }

    public static void main(String[] args) {
        AddMatch addMatch = new AddMatch();
        ListNode node = new ListNode(0);
        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(8);
        ListNode node1 = addMatch.addTwoNumbers(node, node2);
        while (node1.next != null) {
            System.out.println(node1.val);
            node1 = node1.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}