package com.example.familytree.model;

/**
 * Minor subclass of Person.
 * Could later include minor-specific rules (e.g., guardianship).
 */
public class Minor extends Person {

    public Minor(String id, String fullName, Gender gender, int birthYear, Integer deathYear) {
        super(id, fullName, gender, birthYear, deathYear);
        if (getAge() >= 18) {
            throw new IllegalArgumentException("Minor must be under 18 years old");
        }
    }

    // Helper method to get current age
    private int getAge() {
        return ageIn(java.time.Year.now().getValue());
    }
}
