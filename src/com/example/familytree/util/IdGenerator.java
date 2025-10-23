package com.example.familytree.util;


/**
 * Singleton class to generate unique IDs for Person objects.
 */
public final class IdGenerator {

    private static IdGenerator instance;  // single instance
    private int counter = 0;              // internal counter

    // Private constructor prevents instantiation
    private IdGenerator() {}

    // Public method to get the single instance
    public static synchronized IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    /**
     * Generates the next unique ID in the format P001, P002, etc.
     * @return unique ID string
     */
    public synchronized String nextId() {
        counter++;
        return String.format("P%03d", counter);
    }
}
