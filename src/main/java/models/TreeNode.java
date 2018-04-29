package models;

import java.util.LinkedList;
import java.util.List;

public class TreeNode<T> {
    private int id;
    private T auction;
    private TreeNode<T> parent;
    private List<TreeNode<T>> children;

    public TreeNode(T auction) {
        this.auction = auction;
        this.children = new LinkedList<>();
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public TreeNode<T> addChild(T child) {
        TreeNode<T> childNode = new TreeNode<T>(child);
        List<TreeNode<T>> children = this.getChildren();
        childNode.setParent(this);
        children.add(childNode);
        return childNode;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }
}