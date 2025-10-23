package com.example.familytree.strategy;

import com.example.familytreea.model.Person;
import java.util.List;

/**
 * Interface to define methods for querying relatives.
 */
public interface RelativesQuery {

    /**
     * Returns a list of ancestors up to given generations.
     * @param generations number of generations (0 = self)
     * @return list of ancestors
     */
    List<Person> ancestorsOf(int generations);

    /**
     * Returns a list of descendants up to given generations.
     * @param generations number of generations (0 = self)
     * @return list of descendants
     */
    List<Person> descendantsOf(int generations);

    /**
     * Returns a list of siblings (people sharing at least one parent).
     * @return list of siblings
     */
    List<Person> siblings();

    /**
     * Returns the active spouse, or null if not married.
     * @return spouse Person or null
     */
    Person spouse();
}
