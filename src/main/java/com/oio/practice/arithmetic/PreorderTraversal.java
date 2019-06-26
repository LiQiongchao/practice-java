package com.oio.practice.arithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LiQiongchao
 * @Date: 2019/6/26 23:21
 */
public class PreorderTraversal {

    /**
     * @param root: A Tree
     * @return: Preorder in ArrayList which contains node values.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> list = new ArrayList();
        if (root == null) {
            return list;
        }
        list.add(root.val);
        if (root.left != null) {
            list.addAll(preorderTraversal(root.left));
        }
        if (root.right != null) {
            list.addAll(preorderTraversal(root.right));
        }
        return list;
    }

}

class TreeNode {
     public int val;
     public TreeNode left, right;
     public TreeNode(int val) {
         this.val = val;
         this.left = this.right = null;
     }
 }
