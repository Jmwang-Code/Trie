package com.cn.jmw.tree;


import lombok.ToString;

import java.util.*;

/**
 * @author 一只小小狗
 * @version 1.0.0
 * @ClassName BinaryTreeModel.java
 * @Description 二叉树模型
 * @createTime 2022年08月03日 16:40:00
 */
public class BinaryTreeModel {

    //main 主节点
    private TreeNode root;

    /**
     * 初始化树
     *
     * @param val root.value
     */
    public BinaryTreeModel(int val) {
        root = new TreeNode(val);
    }

    public BinaryTreeModel(TreeNode val) {
        root = val;
    }

    @ToString
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 100 判断两颗二叉树是否相同
     *
     * @param p 树1
     * @param q 树2
     * @return boolean
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 101 对称性判断
     *
     * @return boolean
     */
    public boolean isSymmetric() {
        return check(this.root, this.root);
    }

    private boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    /**
     * 104 二叉树最大深度
     *
     * @return Number of layers
     */
    public int maxDepth() {
        return recursionDeep(root);
    }

    private int recursionDeep(TreeNode TreeNode) {
        if (TreeNode == null) {
            return 0;
        } else {
            int leftHeight = recursionDeep(TreeNode.left);
            int rightHeight = recursionDeep(TreeNode.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * 94 中序遍历返回List
     *
     * @return List
     */
    public List<Integer> inorderTraversalForList() {
        List<Integer> res = new ArrayList<Integer>();
        inorder(this.root, res);
        return res;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    /**
     * 114 前序遍历返回List
     *
     * @param root
     * @return
     */
    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        preOrder(list, root);
        return list;
    }

    private void preOrder(List<Integer> list, TreeNode root) {
        if (root == null) return;

        list.add(root.val);
        preOrder(list, root.left);
        preOrder(list, root.right);
    }

    /**
     * 145 后序遍历返回List
     *
     * @param root
     * @return List
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        postOrder(list, root);
        return list;
    }

    private void postOrder(List<Integer> list, TreeNode root) {
        if (root == null) return;

        postOrder(list, root.left);
        postOrder(list, root.right);
        list.add(root.val);
    }

    /**
     * 110 平衡二叉树
     *
     * @return
     */
    public boolean isBalanced() {
        return height(this.root) >= 0;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * 111 最小深度
     *
     * @param root
     * @return List
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.right == null && root.left == null) return 1;

        int mindepth = Integer.MAX_VALUE;
        if (root.right != null) {
            mindepth = Math.min(mindepth, minDepth(root.right));
        }
        if (root.left != null) {
            mindepth = Math.min(mindepth, minDepth(root.left));
        }
        return mindepth + 1;
    }

    /**
     * 112 单条路径总和的值是否为
     *
     * @param targetSum 是否存在目标值为targetSum的
     * @return boolean
     */
    public boolean hasSinglePathSum(int targetSum) {
        return hasSinglePathSumTemp(this.root, targetSum);
    }

    private boolean hasSinglePathSumTemp(TreeNode root, int targetSum) {
        //判断当前是否有val
        if (root == null) {
            return false;
        }
        //可以判断是否是叶子节点
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        //不是叶子节点就继续往下走
        return hasSinglePathSumTemp(root.left, targetSum - root.val) || hasSinglePathSumTemp(root.right, targetSum - root.val);

    }

    /**
     * 623 二叉树中增加一行
     *
     * @param root  根节点
     * @param val   增加的数据
     * @param depth 增加数据的层深
     * @return TreeNode
     */
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        //首先如果等于null 要返回null
        if (root == null) {
            return null;
        }
        //如果层数还有1 那么就在这层进行增加
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        //如果层数等于2 那么就生成新的左右节点
        if (depth == 2) {
            root.left = new TreeNode(val, root.left, null);
            root.right = new TreeNode(val, null, root.right);
        } else {
            root.left = addOneRow(root.left, val, depth - 1);
            root.right = addOneRow(root.right, val, depth - 1);
        }
        return root;
    }

    /**
     * 226 反转二叉树（镜像反转）
     *
     * @return TreeNode
     */
    public TreeNode invertTree() {
        return invertTreeTemp(this.root);
    }

    private TreeNode invertTreeTemp(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = invertTreeTemp(root.right);
        TreeNode left = invertTreeTemp(root.left);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 235  二叉搜索树的最近公共祖先
     *
     * @param root 根节点
     * @param p    p
     * @param q    q
     * @return TreeNode
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode tre = root;

        while (true) {
            if (p.val < tre.val && q.val < tre.val) {
                tre = tre.left;
            } else if (p.val > tre.val && q.val > tre.val) {
                tre = tre.right;
            } else {
                break;
            }
        }
        return tre;
    }


    /**
     * 404 左右节点总和
     *
     * @param root
     * @return int
     */
    final static int LEFT_NODE = 0;
    final static int RIGHT_NODE = 1;

    private int sumOfRightLeaves(TreeNode root) {
        return sumOfLeftOrRightLeaves(root, RIGHT_NODE);
    }

    private int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftOrRightLeaves(root, LEFT_NODE);
    }

    private int sumOfLeftOrRightLeaves(TreeNode root, int type) {
        if (type == 1) {
            invertTreeTemp(root);
        }
        int returnMax = root != null ? sumOfLeftOrRightLeavesTemp(root) : 0;
        invertTreeTemp(root);
        return returnMax;
    }

    private int sumOfLeftOrRightLeavesTemp(TreeNode node) {
        int ans = 0;
        if (node.left != null) {
            ans += isLeafNode(node.left) ? node.left.val : sumOfLeftOrRightLeavesTemp(node.left);
        }
        if (node.right != null && !isLeafNode(node.right)) {
            ans += sumOfLeftOrRightLeavesTemp(node.right);
        }

        return ans;
    }

    /**
     * 是否存在左右子节点
     *
     * @param node
     * @return boolean
     */
    public boolean isLeafNode(TreeNode node) {
        return node.right == null && node.left == null;
    }

    /**
     * 637 二叉树的层平均值
     * BFS
     * @return List<Double>
     */
    public List<Double> averageOfLevels() {
        List<Double> doubles = new ArrayList<>();
        TreeNode temp = this.root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(temp);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                sum += poll.val;
                TreeNode right = poll.right,left = poll.left;
                if (right!=null)queue.offer(right);
                if (left!=null)queue.offer(left);
            }
            doubles.add(sum/size);
        }

        return doubles;
    }


    /**
     * @Author jmw
     * @Description 652 寻找重复子树 这个是DFS+HASHMAP
     * @Date 13:20 2022/9/5
     * @Param
     * @return
     **/
    Map<String,Integer> map = new HashMap<>();
    List<TreeNode> ans = new ArrayList<TreeNode>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return ans;
    }

    String dfs(TreeNode root){
        if(root==null)return " ";
        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append("_");
        sb.append(dfs(root.left)).append(dfs(root.right));
        String key = sb.toString();
        map.put(key,map.getOrDefault(key,0)+1);
        if (map.get(key)==2)ans.add(root);
        return key;
    }


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1,null,null);
        TreeNode treeNode1 = new TreeNode(1,null,null);
        TreeNode treeNode2 = new TreeNode(1, treeNode, null);
        TreeNode treeNode3 = new TreeNode(1, treeNode1, null);
        TreeNode treeNode4 = new TreeNode(1, treeNode2, treeNode3);
        BinaryTreeModel binaryTreeModel = new BinaryTreeModel(treeNode4);
        binaryTreeModel.findDuplicateSubtrees(treeNode4).stream().forEach(System.out::println);

        System.out.println(binaryTreeModel.ans);
    }
}
