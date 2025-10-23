package com.example.familytree.strategy;


import model.Person;
import java.util.*;

/**
 * Breadth-First Traversal implementation of TraversalStrategy.
 */
public class BFSTraversal implements TraversalStrategy {

    @Override
    public List<Person> traverse(Person start, int generations) {
        List<Person> result = new ArrayList<>();
        Queue<Person> queue = new LinkedList<>();
        Map<Person, Integer> levelMap = new HashMap<>();

        queue.add(start);
        levelMap.put(start, 0);

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            int level = levelMap.get(current);
            if (level > generations) continue;

            result.add(current);

            for (Person child : current.getChildren()) {
                queue.add(child);
                levelMap.put(child, level + 1);
            }
        }

        return result;
    }
}
