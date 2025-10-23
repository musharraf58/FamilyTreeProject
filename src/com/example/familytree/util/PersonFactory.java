package com.example.familytree.util;

import model.Adult;
import model.Minor;
import model.Person;
import model.Gender;

/**
 * Factory class to create Person objects.
 * Demonstrates the Factory Method design pattern.
 */
public final class PersonFactory {

    private PersonFactory() {
        // private constructor to prevent instantiation
    }

    /**
     * Creates a Person object (Adult or Minor) based on age.
     * @param id unique ID
     * @param fullName full name
     * @param gender gender
     * @param birthYear birth year
     * @param deathYear death year (nullable)
     * @return Adult or Minor object
     */
    public static Person createPerson(String id, String fullName, Gender gender, int birthYear, Integer deathYear) {
        int currentYear = java.time.Year.now().getValue();
        int age = currentYear - birthYear;
        if (age >= 18) {
            return new Adult(id, fullName, gender, birthYear, deathYear);
        } else {
            return new Minor(id, fullName, gender, birthYear, deathYear);
        }
    }
}
