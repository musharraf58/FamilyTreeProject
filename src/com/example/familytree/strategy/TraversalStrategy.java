package com.example.familytree.strategy;


import model.Person;
import java.util.List;

/**
 * Strategy interface for traversing a family tree.
 */
public interface TraversalStrategy {
    /**
     * Traverses starting from the given person.
     * @param start starting person
     * @param generations how many generations to traverse
     * @return list of persons in traversal order
     */
    List<Person> traverse(Person start, int generations);
}
