package com.oio.practice.arithmetic;

/**
 *
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *  
 *
 * 提示：
 *
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: Liqc
 * @Date: 2021/11/15 19:35
 */
public class MergeTwoSortedLists {

    /**
     * 我们可以用迭代的方法来实现上述算法。当 l1 和 l2 都不是空链表时，
     * 判断 l1 和 l2 哪一个链表的头节点的值更小，将较小值的节点添加到结果里，
     * 当一个节点被添加到结果里之后，将对应链表中的节点向后移一位。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        // 最后只会有一个未合并的，直接把尾指针指向最后一个元素，那样即使后面有多个，也能连起来。
         prev.next = l1 == null? l2 : l1;
        return prehead.next;
    }

}
