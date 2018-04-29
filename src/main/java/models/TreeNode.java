package models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeNode<T> {
    private int id;
    private String name;
    private TreeNode<T> parent;
    private List<TreeNode<T>> children;

    private List<Auction> auctions;

    public TreeNode(int id, String name) {
        this.id = id;
        this.name = name;
        auctions = new ArrayList<>();
        children = new LinkedList<>();
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public TreeNode<T> addChild(TreeNode<T> child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public void deleteChild(TreeNode<T> child) {
        child.getParent().getChildren().clear();
    }

    public void addAuction(Auction auction) {
        this.auctions.add(auction);
    }

    public int branchesToRoot() {
        int level = 0;
        if (!isRoot()) {
            level = this.getParent().branchesToRoot() + 1;
        }
        return level;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public TreeNode<T> searchById(int categoryID) {
        if (categoryID == this.id) {
            return this;
        } else {
            for (TreeNode<T> child : this.children) {
                if (child.searchById(categoryID) != null) {
                    return child.searchById(categoryID);
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return id + ". " + name;
    }
}