package com.cn.jmw.tree.bfs;

import com.cn.jmw.tree.bean.TreeNode;

import java.util.*;

/**
 * @author jmw
     * @Description 特定深度节点链表
 * 中规中矩 DFS BFS
 * 但是他里面的这种做法 效率会高这么多 我不理解
 * @date 2022年08月26日 18:10
 * @Version 1.0
 */
public class SpecificDepthNodeLinkedList0403 {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * @Author jmw
     * @Description /高效做法
     * @Date 18:20 2022/8/26
     * @Param [com.cn.jmw.tree.bean.TreeNode]
     * @return com.cn.jmw.tree.bfs.SpecificDepthNodeLinkedList0403.ListNode[]
     **/
    public ListNode[] listOfDepth(TreeNode tree) {
        ArrayList<ListNode> res = new ArrayList<ListNode>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode listHead = null;
            ListNode listTail = null;
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                // create list
                if (listHead == null) {
                    listHead = new ListNode(treeNode.val);
                    listTail = listHead;
                } else {
                    listTail.next = new ListNode(treeNode.val);
                    listTail = listTail.next;
                }
                // add queue
                if (treeNode.left != null) queue.add(treeNode.left);
                if (treeNode.right != null) queue.add(treeNode.right);
            }
            // add list to result
            if (listHead != null) {
                res.add(listHead);
            }
        }

        ListNode[] ret = new ListNode[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }

    public ListNode[] listOfDepth2(TreeNode tree) {
        if (tree == null) return new ListNode[0];
        List<ListNode> ans = new ArrayList();
        Deque<TreeNode> queue = new ArrayDeque();
        queue.add(tree);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode cur = null;
            while (size-- > 0) {
                TreeNode node = queue.pop();
                ListNode t = new ListNode(node.val);
                t.next = cur;
                cur = t;
                if (node.right != null) queue.add(node.right);
                if (node.left != null) queue.add(node.left);
            }
            ans.add(cur);
        }
        return ans.toArray(new ListNode[ans.size()]);
    }
}
