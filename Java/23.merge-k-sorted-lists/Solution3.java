import java.util.*;
/*
 * @lc app=leetcode.cn id=23 lang=java
 *
 * [23] 合并K个排序链表
 *
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/description/
 *
 * algorithms
 * Hard (43.81%)
 * Total Accepted:    17.3K
 * Total Submissions: 39K
 * Testcase Example:  '[[1,4,5],[1,3,4],[2,6]]'
 *
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 
 * 示例:
 * 
 * 输入:
 * [
 *  1->4->5,
 *  1->3->4,
 *  2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        current.next = (l1 != null) ? l1 : l2;
        return dummyHead.next;
    }

    private ListNode mergeLists(ListNode[] lists, int begin, int end) {  
        if (begin < end) {
            int mid = (begin + end) / 2;
            ListNode leftList = mergeLists(lists, begin, mid);
            ListNode rightList = mergeLists(lists, mid + 1, end);
            return merge(leftList, rightList);
        }
        return lists[begin];
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeLists(lists, 0, lists.length - 1);
    }
}

