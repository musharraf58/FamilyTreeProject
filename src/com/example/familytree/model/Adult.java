package com.example.familytree.model;


/**
 * Adult subclass of Person.
 * Could later include adult-specific rules (e.g., can marry, manage finances).
 */
public class Adult extends Person {

    public Adult(String id, String fullName, Gender gender, int birthYear, Integer deathYear) {
        super(id, fullName, gender, birthYear, deathYear);
        if (getAge() < 18) {
            throw new IllegalArgumentException("Adult must be at least 18 years old");
        }
    }

    // Helper method to get current age
    private int getAge() {
        return ageIn(java.time.Year.now().getValue());
    }
}
