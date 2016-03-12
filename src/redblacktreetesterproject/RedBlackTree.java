/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktreetesterproject;

/**
 * a red black tree.
 * @author ruijieouyang
 * @version 1.0
 * @since 10/05/2015
 */
public class RedBlackTree {

    //private RedBlackNode sentinel;
    private RedBlackNode nil;
    private RedBlackNode root;
    private int numOfCompare;
    private int size;

    /**
     * Notes from CLR "Introduction To Algorithms"
     *
     * Reb Black Trees A red-black tree is a binary search tree with an extra
     * bit of storage per node. The extra bit represents the color of the node.
     * It's either red or black. Each node contains the fields: color, key,
     * left, right, and p. Any nil pointers are regarded as pointers to external
     * nodes (leaves) and key bearing nodes are considered as internal nodes of
     * the tree.
     *
     * Red-black tree properties: 1. Every node is either red or black. 2. The
     * root is black. 3. Every leaf (nil) is black. 4. If a node is red then
     * both of its children are black. 5. For each node, all paths from the node
     * to descendant leaves contain the same number of black nodes.
     *
     * From these properties, it can be shown (by an iduction proof) that the
     * tree has a height no more than 2 * Lg(n + 1).
     *
     * In the implementation of the tree, we use a single node to represent all
     * of the external nulls. Its color will always be black. The parent pointer
     * (p) in the root will point to this node and so will all the internal
     * nodes that would normally contain a left or right value of null. In other
     * words, instead of containing a null pointer as a left child or a null
     * pointer as a right child, these internal nodes will point to the one node
     * that represents the external nulls.
     *
     * This constructor creates an empty RedBlackTree. It creates a RedBlackNode
     * that represents null. It sets the internal variable tree to point to this
     * node. This node that an empty tree points to will be used as a sentinel
     * node. That is, all pointers in the tree that would normally contain the
     * value null, will instead point to this sentinel node.
     */
    public RedBlackTree() {
        nil = new RedBlackNode("", RedBlackNode.BLACK, null, null, null);
        root = nil;
        root.setLc(nil);
        root.setP(nil);
        root.setRc(nil);
        size = 0;
        //numOfCompare = 0;

    }

    /**
     * pre:n/a
     * post:get the size of the tree
     * @return number of values inserted into the tree. 
     * Big-Theta(1).
     */
    public int getSize() {
        // return 2^(height()/2-1);
        return size;
    }

    /**
     * Perform an inorder traversal of the tree. The
     * inOrderTraversal(RedBlackNode) method is recursive and displays the
     * content of the tree. It makes calls on System.out.println(). This method
     * would normally be private.
     *
     * @param t - the root of the tree on the first call. 
     * Big-Theta(1):best case if the node t is a leaf
     * Big-Theta(n):worst case if the node t is root.
     */
    private void inOrderTraversal(RedBlackNode t) {
        if (t.getLc() != nil) {
            inOrderTraversal(t.getLc());
        }
        System.out.println("[data = " + t.getData()
                + ":Color = " + (t.getColor() == 0 ? "BLACK" : "RED")
                + ":Parent = " + (t.getP() == nil ? "-1" : t.getP().getData())
                + ": LC = " + (t.getLc() == nil ? "-1" : t.getLc().getData())
                + ": RC = " + (t.getRc() == nil ? "-1" : t.getRc().getData())
                + "]");
        //System.out.println(t.getData());
        if (t.getRc() != nil) {
            inOrderTraversal(t.getRc());
        }

    }

    /**
     * The no argument inOrderTraversal() method calls the recursive
     * inOrderTraversal(RedBlackNode) - passing the root.
     * Big-Theta(n):for all cases
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    /**
     * Perform a reverseOrder traversal of the tree. The
     * reverseOrderTraversal(RedBlackNode) method is recursive and displays the
     * content of the tree in reverse order. This method would normally be
     * private.
     *
     * @param t - the root of the tree on the first call.
     * Big-Theta(1):best case if the node t is a leaf
     * Big-Theta(n):worst case if the node t is root.
     */
    private void reverseOrderTraversal(RedBlackNode t) {
        if (t.getRc() != nil) {
            reverseOrderTraversal(t.getRc());
        }
        System.out.println("[data = " + t.getData()
                + ":Color = " + (t.getColor() == 0 ? "BLACK" : "RED")
                + ":Parent = " + (t.getP() == nil ? "-1" : t.getP().getData())
                + ": LC = " + (t.getLc() == nil ? "-1" : t.getLc().getData())
                + ": RC = " + (t.getRc() == nil ? "-1" : t.getRc().getData())
                + "]");
        if (t.getLc() != nil) {
            reverseOrderTraversal(t.getLc());
        }

    }

    /**
     * The no argument reverseOrderTraversal() method calls the recursive
     * reverseOrderTraversal(RedBlackNode) - passing the root.
     * Big-Theta(n):for all cases
     */
    public void reverseOrderTraversal() {
        reverseOrderTraversal(root);

    }

    /**
     * The insert() method places a data item into the tree.
     * Pre-condition:memory is available for insertion
     * Big-Theta(log(n)):for all cases
     * 
     * @param value - is an integer to be inserted.
     * 
     */
    public void insert(String value) {
        RedBlackNode z = new RedBlackNode(value, RedBlackNode.BLACK, null, null, null);

        RedBlackNode y = nil;
        RedBlackNode x = root;
        while (x != nil) {
            y = x;
            if (value.compareTo(x.getData()) < 0) {
                x = x.getLc();
            } else {
                x = x.getRc();
            }
        }
        z.setP(y);
        if (y == nil) {
            root = z;
        } else {
            if (value.compareTo(y.getData()) < 0) {
                y.setLc(z);
            } else {
                y.setRc(z);
            }
        }
        size++;
        z.setLc(nil);
        z.setRc(nil);
        z.setColor(RedBlackNode.RED);
        RBInsertFixup(z);
    }

    /**
     * leftRotate() performs a single left rotation. 
     * pre: right[x] != nil[T]
     * pre: root's parent is nill[T]
     * Big-Theta(1):for all cases
     * @param x
     */
    public void leftRotate(RedBlackNode x) {
        RedBlackNode y;
        y = x.getRc();
        x.setRc(y.getLc());
        y.getLc().setP(x);
        y.setP(x.getP());

        if (x.getP() == nil) {
            root = y;
        } else {
            if (x == x.getP().getLc()) {
                x.getP().setLc(y);
            } else {
                x.getP().setRc(y);
            }
        }
        y.setLc(x);
        x.setP(y);
    }

    /**
     * rightRotate() performs a single right rotation. 
     * pre: left[x] != nil[T]
     * pre: root's parent is nill[T]
     * Big-Theta(1):for all case
     * 
     * @param x
     */
    public void rightRotate(RedBlackNode x) {
        RedBlackNode y;
        y = x.getLc();// y now points to node to left of x
        x.setLc(y.getRc());//y's right subtree becomes x's left subtree
        y.getRc().setP(x);// right subtree of y gets a new parent
        y.setP(x.getP());// y's parent is now x's parent

        // if x is at root then y becomes new root
        if (x.getP() == nil) {
            root = y;
        } else {// if x is a left child then adjust x's parent's left child or...
            if (x == x.getP().getLc()) {
                x.getP().setLc(y);
            } else {
                // adjust x's parent's right child
                x.getP().setRc(y);
            }
        }
        // the right child of y is now x
        y.setRc(x);
        // the parent of x is now y
        x.setP(y);

    }

    /**
     * Fixing up the tree so that Red Black Properties are preserved.
     * Big-Theta(log(n)):worst case when red can cascade all the way to the root.
     * The cascade is proportional to the height of the tree, so the fixin' takes O(log n), worst case.
     * Big-Theta(1):worst case when z.parent.color is black.
     * @param z
     */
    public void RBInsertFixup(RedBlackNode z) {
        RedBlackNode y;
        while (z.getP().getColor() == RedBlackNode.RED) {
            if (z.getP() == z.getP().getP().getLc()) {
                y = z.getP().getP().getRc();
                if (y.getColor() == RedBlackNode.RED) {
                    z.getP().setColor(RedBlackNode.BLACK);
                    y.setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    z = z.getP().getP();
                } else {
                    if (z == z.getP().getRc()) {
                        z = z.getP();
                        leftRotate(z);
                    }
                    z.getP().setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    rightRotate(z.getP().getP());
                }
            } else {
                y = z.getP().getP().getLc();
                if (y.getColor() == RedBlackNode.RED) {
                    z.getP().setColor(RedBlackNode.BLACK);
                    y.setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    z = z.getP().getP();
                } else {
                    if (z == z.getP().getLc()) {
                        z = z.getP();
                        this.rightRotate(z);
                    }
                    z.getP().setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    this.leftRotate(z.getP().getP());
                }
            }
        }
        root.setColor(RedBlackNode.BLACK);

    }

    /**
     * The boolean contains() returns true if the String v is in the
     * RedBlackTree and false otherwise. It counts each comparison it makes in
     * the variable recentCompares.
     * Big-Theta(log(n)):worst case when traverse all left/right nodes to find v
     * Big-Theta(1):best case when v is root's data.
     * @param v x - the value to search for
     * @return true if v is in the tree, false otherwise;
     *
     */
    public boolean contains(java.lang.String v) {
        numOfCompare = 0;
        //initialize a pointer to the root to traverse the tree
        RedBlackNode current = root;
        //while we haven't reached the end of the tree
        while (current != nil) {
            numOfCompare++;
            //if we have found a node with data equal to v,return true.
            if (current.getData().equals(v)) {
                return true;
            } //go left or right based on value of current and data
            else if (current.getData().compareTo(v) < 0) {
                current = current.getRc();
            } else {
                current = current.getLc();
            }

        }
        //have not found any node contains v
        return false;

    }

    /**
     * Big-Theta(1):for all cases
     * @return number of comparisons made in last call on the contains method.
     */
    public int getRecentCompares() {
        return numOfCompare;

    }

    /**
     * The method closeBy(v) returns a value close to v in the tree. If v is
     * found in the tree it returns v.
     * Big-Theta(log(n)):worst case when traverse all left/right nodes to find v
     * Big-Theta(1):best case when v is root's data.
     * @param v - the value to search close by for.
     * @return
     */
    public String closeBy(String v) {
        //initialize a pointer to the root to traverse the tree
        RedBlackNode current = root;
        //while we haven't reached the end of the tree
        while (current != nil && current.getLc() != nil && current.getRc() != nil) {
            if (current.getData().equals(v)) {
                //return current.getData();
                break;
            } //go left or right based on value of current and data
            else if (current.getData().compareTo(v) < 0) {
                current = current.getRc();
            } else {
                current = current.getLc();
            }

        }
        //have not found any node contains v
        return current.getData();

    }

    /**
     * This a recursive routine that is used to compute the height of the red
     * black tree. It is called by the height() method. The height() method
     * passes the root of the tree to this method. This method would normally be
     * private.
     * Big-Theta(1):best case for t is leaf
     * Big-Theta(log(n)):worst case for t is root
     * @param t - a pointer to a node in the tree.
     * @return the height of node t
     */
    public int height(RedBlackNode t) {
        if (t == nil) {
            return -1;
        }
        return 1 + Math.max(height(t.getLc()), height(t.getRc()));
    }

    /**
     * This method calls the recursive method.
     * Big-Theta(log(n)):for all case
     * @return the height of the red black tree.
     */
    public int height() {
        return height(root);
    }

    /**
     * This method displays the RedBlackTree in level order. It first displays
     * the root. Then it displays all children of the root. Then it displays all
     * nodes on level 3 and so on. It is not recursive. It uses a queue.
     * Big-Theta(n):for all cases
     */
    public void levelOrderTraversal() {
        Queue q = new Queue();
        q.enQueue(root);
        while (!q.isEmpty()) {
            RedBlackNode p = (RedBlackNode) q.deQueue();
            System.out.println("[data = " + p.getData()
                    + ":Color = " + (p.getColor() == 0 ? "BLACK" : "RED")
                    + ":Parent = " + (p.getP() == nil ? "-1" : p.getP().getData())
                    + ": LC = " + (p.getLc() == nil ? "-1" : p.getLc().getData())
                    + ": RC = " + (p.getRc() == nil ? "-1" : p.getRc().getData())
                    + "]");
            if (p.getLc() != nil) {
                q.enQueue(p.getLc());
            }
            if (p.getRc() != nil) {
                q.enQueue(p.getRc());
            }
        }

    }

    /**
     *
     * @param args - no command line arguments
     */
    public static void main(String[] args) {

        RedBlackTree rbt = new RedBlackTree();

        for (int j = 1; j <= 5; j++) {
            rbt.insert("" + j);
        }

        // after 1..5 are inserted
        System.out.println("RBT in order");
        rbt.inOrderTraversal();
        System.out.println("RBT level order");
        rbt.levelOrderTraversal();

        // is 3 in the tree
        if (rbt.contains("" + 3)) {
            System.out.println("Found 3");
        } else {
            System.out.println("No 3 found");
        }

        // display the height
        System.out.println("The height is " + rbt.height());

    }

}
