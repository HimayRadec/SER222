//package edu.ser222.m01_03;

/**
 * This program provides an implementation of the Deque interface. Also provides a main that
 * demonstrates it.
 *
 * @author Himay, Acuna
 * @version (version)
 */


import java.util.NoSuchElementException;

public class CompletedDeque<Item> implements Deque<Item> {

    //TODO: implement all the methods

    /**
     * Program entry point for deque.
     * @param args command line arguments
     */

    private Node<Item> front;
    private Node<Item> back;
    private int size;

    public CompletedDeque() {
        front = null;
        back = null;
        size = 0;
    }

    @Override
    public void enqueueFront(Item element) {
        // Create new Node object
        Node<Item> node = new Node<>(element);


        // Check if the queue is empty
        if (isEmpty()) {
            front = node;
            back = node;
        }
        else {
            node.next = front;
            front.prev = node;
            front = node;
        }

        // Increment the size of the deque
        size++;
    }

    @Override
    public void enqueueBack(Item element) {
        Node<Item> node = new Node<>(element);

        if (isEmpty()) {
            front = node;
            back = node;
        }
        else {
            node.prev = back;
            back.next = node;
            back = node;
        }
        size++;
    }

    @Override
    public Item dequeueFront() throws NoSuchElementException {
        // Implement this method to remove and return the element at the front of the deque.

        if (isEmpty()) throw new NoSuchElementException("No element exists");
        else {

            // assign front item to a variable
            Item frontItem = front.data;

            // Check if front is not the only item there
            if (front.next != null) {
                // update front node
                front = front.next;
                front.prev = null;
            }
            // if it was the only item
            else {
                front = null;
                back = null;
            }
            size--;
            return frontItem;
        }
    }

    @Override
    public Item dequeueBack() throws NoSuchElementException {
        // Implement this method to remove and return the element at the back of the deque.
        if (isEmpty()) throw new NoSuchElementException("Queue is empty!");
        else {
            // Assign back item to a variable
            Item backItem = back.data;

            // Check if it isn't the only item in the queue
            if (back.prev != null) { // could also check if size is == 1

                // update back node
                back = back.prev;
                back.next = null;
            }
            // if it was the only item in the queue
            else {
                front = null;
                back = null;
            }
            size--;
            return backItem;
        }
    }

    @Override
    public Item first() throws NoSuchElementException {
        // Implement this method to return the element at the front of the deque without removing it.
        if (isEmpty()) throw new NoSuchElementException("Queue is empty!");
        else return front.data;

    }

    @Override
    public Item last() throws NoSuchElementException {
        // Implement this method to return the element at the back of the deque without removing it.
        if (isEmpty()) throw new NoSuchElementException("Queue is empty!");
        else return back.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Queue is empty!";
        }

        StringBuilder sb = new StringBuilder();

        // This will track the current node we are on as we iterate through the loop
        Node<Item> node = back;

        while (node != null) {
            sb.append(node.data);

            // check if is the last node and add some space for better formatting
            if (node.prev != null) {
                sb.append(" ");
            }
            node = node.prev;
        }

        return sb.toString();
    }


    // Create a node class
    private static class Node<Item> {
        Item data;
        Node<Item> next;
        Node<Item> prev;

        Node(Item data) {
            this.data = data;
        }
    }
}
