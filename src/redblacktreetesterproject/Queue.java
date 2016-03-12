/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktreetesterproject;

/**
 *
 * The Queue is a first in first out data structure. This Queue holds Java
 * Object references. It grows dynamically as long as memory is available. Note:
 * Many of these Javadoc comments describe implementation details. For
 * non-classroom use, these implementation details would not be exposed.
 *
 * @author ruijieouyang
 */
public class Queue {

    private Object[] queue;
    private int front;
    private int rear;
    private int size;
    private int count;

    /**
     * Create an empty queue that lives in a small array. The queue class will
     * begin with an array of size 10. Pre: Memory is available. Post: Array
     * created and indexes established.
     */
    public Queue() {
        size = 10;
        queue = (Object[]) new Object[size];
        front = -1;
        rear = -1;
        count = 0;
    }

    /**
     * Boolean method returns true on empty queue, false otherwise. Pre: None
     *
     * @return true if queue is empty.
     */
    public boolean isEmpty() {
       // return front == 0 && rear == 0;
        return count == 0;
    }

    /**
     * Boolean method returns true if queue is currently at capacity, false
     * otherwise. If full returns true and additional enqueue calls are made,
     * the queue will expand in size. 
     * Pre: None
     *
     * @return Returns true if queue is at current capacity.
     *
     */
    public boolean isFull() {

      //  return rear == front-1;
        return count ==size;
    }

    /**
     * Object method removes and returns reference in front of queue.
     * Pre-condition: queue not empty
     *
     * @return object in front of queue.
     */
    public Object deQueue() {
        Object result = queue[front];
        queue[front] = null;
        front = (front+1)%size;
        count--;
        return result;
        
    }

    /**
     * Add an object reference to the rear of the queue. Pre-condition: Memory
     * is available for doubling queue capacity when full. Post-condition: queue
     * now contains x in the rear.
     *
     * @param x - is an object to be added to the rear of the queue.
     */
    public void enQueue(Object x) {
        if (this.isEmpty()) {
            front = 0;
            rear = 0;
        } else if(this.isFull()) {
            //expand capacity
            size = 2 * size;

            Object[] doubleSizeQueue = (Object[]) new Object[size];
            for (int i = 0; i < count; i++) {
                doubleSizeQueue[i] = queue[front];
                front = (front+1) % (size/2);
            }
            //insert into the new expanded queue
            front = 0;
            rear = count;
            queue = doubleSizeQueue;
            
        }
        else{
           rear = (rear + 1) % size; 
        }
        queue[rear] = x;
        
        count++;

    }

    /**
     * Method getFront returns the front of the queue without removing it.
     * Pre-condition: queue not empty
     *
     * @return the queue front without removal.
     *
     */
    public Object getFront() {
        return queue[front];
    }

    /**
     * The toString method returns a String representation of the current queue
     * contents.
     *
     * @return a string representation of the queue. It shows the front of the
     * queue first. It then shows the second and third and so on.
     */
    @Override
    public String toString() {
        String result = "";
        int i = 0;
        while(i<count){
            if(queue[i]!=null){
                result += queue[i].toString()+"\n";
            }
            i++;
        }
        
        return result;
    }

    /**
     * main is for testing the queue routines.
     *
     * @param a - Command line parameters are not used.
     *
     */
    public static void main(java.lang.String[] a) {
    }

}
