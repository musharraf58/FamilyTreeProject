package com.example.familytree.strategy;

import model.Person;
import java.util.ArrayList;
import java.util.List;

/**
 * Depth-First Traversal implementation of TraversalStrategy.
 */
public class DFSTraversal implements TraversalStrategy {

    @Override
    public List<Person> traverse(Person start, int generations) {
        List<Person> result = new ArrayList<>();
        dfsHelper(start, generations, 0, result);
        return result;
    }

    private void dfsHelper(Person p, int maxGen, int currentGen, List<Person> list) {
        if (currentGen > maxGen) return;
        list.add(p);
        for (Person child : p.getChildren()) {
            dfsHelper(child, maxGen, currentGen + 1, list);
        }
    }
}
