/**
 * A symbol table implemented using a hashtable with linear probing.
 * 
 * @author (put your name here), Sedgewick and Wayne, Acuna
 */

//package edu.ser222.m03_04;
import java.util.LinkedList;
import java.util.Queue;

public class CompletedLinearProbingHT<Key, Value> implements ProbingHT<Key, Value> {
    private int N;
    private int M;
    private final LinearProbe<Key, Value>[] hashTable;

    public static class LinearProbe<Key, Val> {
        private Key key;
        private Val value;

        public LinearProbe(Key key, Val value) {
            this.key = key;
            this.value = value;
        }

    }

    public CompletedLinearProbingHT() {
        this(997);
    }

    public CompletedLinearProbingHT(int size) {
        this.M = size;
        this.N = 0;
        this.hashTable = new LinearProbe[M];

    }

    //any constructors must be made public

    @Override
    public int hash(Key key, int i) {
        return ((key.hashCode() & 0x7fffffff) + i) % M;
    }

    @Override
    public void put(Key key, Value val) {
        int i = 0;
        for (i = hash(key, i); hashTable[i] != null; i = (i + 1) % M) {

            // check if key already exists
            if (hashTable[i].key.equals(key)) {
                hashTable[i].value = val;
                return;
            }
        }
        hashTable[i] = new LinearProbe<Key, Value>(key, val);
        N++;

    }

    @Override
    public Value get(Key key) {
        int i = 0;
        for (i = hash(key, i); hashTable[i] != null; i = (i + 1) % M) {
            if (hashTable[i].key.equals(key)) {
                return hashTable[i].value;
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        int i = 0;
        for (i = hash(key, i); hashTable[i] != null; i = (i + 1) % M) {
            if (hashTable[i].key.equals(key)){
                for (int j = i + 1; hashTable[i] != null ; j = (j + 1) % M) {
                    hashTable[i] = hashTable[j];
                    i = (i + 1) % M;
                }
                hashTable[i] = null;
                N--;
            }
        }
    }


    @Override
    public boolean contains(Key key) {
        return get(key) != null;
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

        Queue<Key> queue = new LinkedList<Key>();
        for (int i = 0; i < M; i++)
            if (hashTable[i] != null) queue.add(hashTable[i].key);
        return queue;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THESE METHODS ARE ONLY FOR GRADING AND COME FROM THE PROBINGHT INTERFACE.

    @Override
    public int getM() {
        //TODO. We suggest something like:
        return M;

    }

    @Override
    public Object getTableEntry(int i) {
        //TODO. We suggest something like:
        return hashTable[i];

    }
}