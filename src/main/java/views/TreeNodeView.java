package views;

import models.Auction;
import models.TreeNode;

public class TreeNodeView {
    public void viewCategories(TreeNode<Auction> root) {
        System.out.println(root);
        for (TreeNode<Auction> child : root.getChildren()) {
            for (int i = 0; i < child.branchesToRoot(); i++) {
                System.out.print("   ");
            }
            viewCategories(child);
        }
    }
}
