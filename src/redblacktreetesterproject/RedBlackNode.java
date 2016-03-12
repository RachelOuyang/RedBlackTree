/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktreetesterproject;

/**
 * node in red black tree.
 * @author ruijieouyang
 * @version 1.0
 * @since 10/05/2015
 */
public class RedBlackNode {

    public static final int BLACK = 0;
    public static final int RED = 1;
    private String data;
    private int color;
    private RedBlackNode p;
    private RedBlackNode lc;
    private RedBlackNode rc;

    /**
     * Construct a RedBlackNode with data, color, parent pointer, left child
     * pointer and right child pointer.
     *
     * @param data - a simple value held in the tree
     * @param color - either RED or BLACK
     * @param p - the parent pointer
     * @param lc -the pointer to the left child (will be null only for the node
     * that represents all external nulls.
     * @param rc- the pointer to the right child (will be null only for the node
     * that represents all external nulls.
     */
    public RedBlackNode(String data, int color, RedBlackNode p, RedBlackNode lc, RedBlackNode rc) {
        this.data =data;
        this.color = color;
        this.p =p;
        this.lc = lc;
        this.rc = rc;
    }

    /**
     * The toString() methods returns a string representation of the
     * RedBlackNode.
     *
     * @return the string representation of a RedBlackNode
     */
    @Override
    public String toString() {
        String result;
        result = "The node is " + color + " and with value " + data;
        result += " Parent is " + p.getColor() + " and with value " + p.getData();
        result += "Left Sibling is " + lc.getColor() + " and with value " + lc.getData();
        result += "Right Sibling is " + rc.getColor() + " and with value " + rc.getData();
        return result;
    }
    
    /**
     * The getColor() method returns RED or BLACK.

     * @return the color value (RED or BLACK)
     */
    
    public int getColor(){
       return color; 
    }
    /**
     * The getData() method returns the data in the node.
     * @return the data value in the node
     */
    public String getData(){
        return data;
    }
    
    /**
     * The getLc() method returns the left child of the RedBlackNode.
     * @return the left child field
     */
    public RedBlackNode getLc(){
        return lc;
    }
    
    /**
     * The getP() method returns the parent of the RedBlackNode.
     * @return the parent field
     */
    public RedBlackNode getP(){
        return p;
    }
    /**
     * The getRc() method returns the right child of the RedBlackNode.
     * @return the right child field
     */
    public RedBlackNode getRc(){
        return rc;
    }
    
    /**
     * The setColor() method sets the color of the RedBlackNode.
     * @param color - is either RED or BLACK
     */
    public void setColor(int color){
        this.color = color;
    }
    
    /**
     * The setData() method sets the data or key of the RedBlackNode.
     * @param data - is an int holding a node's data value
     */
    public void setData(String data) {
        this.data = data;
    }
    
    /**
     * The setP() method sets the parent of the RedBlackNode.
     * @param p - establishes a parent pointer for this node
     */
    public void setP(RedBlackNode p) {
        this.p = p;
    }
    
    /**
     * The setLc() method sets the left child of the RedBlackNode.
     * @param lc - establishes a left child for this node
     */
    public void setLc(RedBlackNode lc) {
        this.lc = lc;
    }
    
    /**
     * The setRc() method sets the right child of the RedBlackNode.
     * @param rc - establishes a right child for this node.
     */
    public void setRc(RedBlackNode rc) {
        this.rc = rc;
    }
    
    
    




}
