/**
 * A symbol table implemented using a hashtable with chaining.
 * Does not support load balancing or resizing.
 * 
 * @author (put your name here), Sedgewick and Wayne, Acuna
 */

//package edu.ser222.m03_04;

import java.util.LinkedList;

public class CompletedTwoProbeChainHT<Key, Value> implements TwoProbeChainHT<Key, Value> {
    private int M; // size of hashtable
    private int N; // number of key-value pairs in hashtable
    private LinkedList<Entry>[] symbolTable;

    public static class Entry<Key, Val> {
        private final Key key;
        private Val value;

        public Entry(Key key, Val value) {
            this.key = key;
            this.value = value;
        }
    }

    //any constructors must be made public

    public CompletedTwoProbeChainHT() {
        this(997);
    }

    public CompletedTwoProbeChainHT(int size) {
        this.M = size;
        this.N = 0;

        // Create a new array of lists.
        symbolTable = (LinkedList<Entry>[]) new LinkedList[M];

        // Initialize each linked list in the array
        for (int i = 0; i < M; i++) {
            symbolTable[i] = new LinkedList<>();
        }

    }

    @Override
    public int hash(Key key) {
        int M = getM();

        return (key.hashCode() & 0x7fffffff) % M;
    }

    @Override
    public int hash2(Key key) {
        int M = getM();
        return (((key.hashCode() & 0x7fffffff) % M) * 31) % M;
    }

    @Override
    public void put(Key key, Value val) {

        // If the key already exists, update the value
        for (int i = 0; i < symbolTable[hash(key)].size(); i++) {

            if (symbolTable[hash(key)].get(i).key.equals(key)) {
                symbolTable[hash(key)].get(i).value = val;
                return;
            }

        }

        // If the key already exists, update the value
        for (int i = 0; i < symbolTable[hash2(key)].size(); i++) {

            if (symbolTable[hash2(key)].get(i).key.equals(key)) {
                symbolTable[hash2(key)].get(i).value = val;
                return;
            }

        }

        // If the key does not exist, add it to the hashtable depending on which list is smaller
        if (symbolTable[hash(key)].size() < symbolTable[hash2(key)].size()) {
            symbolTable[hash(key)].add(new Entry(key, val));
        }
        else {
            symbolTable[hash2(key)].add(new Entry(key, val));
        }
        N++;

    }


    @Override
    public Value get(Key key) {

        // If the key is found, return the value
        for (int i = 0; i < symbolTable[hash(key)].size(); i++) {
            if (symbolTable[hash(key)].get(i).key.equals(key)) {
                return (Value) symbolTable[hash(key)].get(i).value;
            }
        }

        for (int i = 0; i < symbolTable[hash2(key)].size(); i++) {
            if (symbolTable[hash2(key)].get(i).key.equals(key)) {
                return (Value) symbolTable[hash2(key)].get(i).value;
            }

        }
        return null;

    }


    @Override
    public void delete(Key key) {

        // If the key is found, remove it
        for (int i = 0; i < symbolTable[hash(key)].size(); i++) {
            if (symbolTable[hash(key)].get(i).key.equals(key)) {
                symbolTable[hash(key)].remove(i);
                N--;
            }
        }

        for (int i = 0; i < symbolTable[hash2(key)].size(); i++) {
            if (symbolTable[hash2(key)].get(i).key.equals(key)) {
                symbolTable[hash2(key)].remove(i);
                N--;
            }

        }
    }

    @Override
    public boolean contains(Key key) {
        // If the key is found, remove it
        for (int i = 0; i < symbolTable[hash(key)].size(); i++) {
            if (symbolTable[hash(key)].get(i).key.equals(key)) {
                return true;
            }
        }

        for (int i = 0; i < symbolTable[hash2(key)].size(); i++) {
            if (symbolTable[hash2(key)].get(i).key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<Key> keys() {
        LinkedList<Key> list = new LinkedList<Key>();

        // Add all keys to the list
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < symbolTable[i].size(); j++) {
                list.add((Key) symbolTable[i].get(j).key);
            }
        }
        return list;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THESE METHODS ARE ONLY FOR GRADING AND COME FROM THE TWOPROBECHAINHT INTERFACE.

    @Override
    public int getM() {
        //TODO. We suggest something like:
        return M;

    }

    @Override
    public int getChainSize(int i) {
        //TODO. We suggest something like:
        return symbolTable[i].size();

    }
}