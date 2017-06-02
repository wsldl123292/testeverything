package leetcode.tree;

import algs4.bsq.Queue;

import java.util.LinkedList;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/5/27 10:37
 */
public class Tree {

    public static int maxDepth(TreeNode root) {
        System.out.println(root == null ? 111 : root.val);
        return root == null ? 0 : Math.min(maxDepth(root.left), maxDepth(root.right)) + 1;

    }

    public static int nodeNum(TreeNode treeNode) {
        return treeNode == null ? 0 : nodeNum(treeNode.left) + nodeNum(treeNode.right) + 1;
    }

    public static void preOrderTraverse(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.println(treeNode.val);
            preOrderTraverse(treeNode.left);
            preOrderTraverse(treeNode.right);
        }

    }

    public static void inOrderTraverse(TreeNode treeNode) {
        if (treeNode != null) {
            inOrderTraverse(treeNode.left);
            System.out.println(treeNode.val);
            inOrderTraverse(treeNode.right);
        }

    }

    public static void postOrderTraverse(TreeNode treeNode) {
        if (treeNode != null) {
            postOrderTraverse(treeNode.left);
            postOrderTraverse(treeNode.right);
            System.out.println(treeNode.val);
        }

    }

    public static void levelTraverse(TreeNode treeNode) {
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        if(treeNode == null){
            return;
        }

        treeNodes.push(treeNode);
        System.out.println(treeNode.val);
        while(!treeNodes.isEmpty()){
            TreeNode node = treeNodes.poll();
            if(node.left != null){
                treeNodes.push(node.left);
                System.out.print(node.left.val);
            }
            if (node.right != null) {
                treeNodes.push(node.right);
                System.out.print(node.right.val);
            }

        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(0);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(2);
        treeNode.left.left = new TreeNode(3);
        treeNode.left.right = new TreeNode(4);
        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(6);
        //System.out.println(nodeNum(treeNode));
        //postOrderTraverse(treeNode);
        levelTraverse(treeNode);
    }
}
