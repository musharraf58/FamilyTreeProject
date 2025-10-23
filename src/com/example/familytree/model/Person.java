package com.example.familytree.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for all people in the family tree.
 * Demonstrates encapsulation, inheritance, and basic validation.
 */
public abstract class Person {
    // --- Private fields (encapsulation) ---
    private final String id;            // Unique ID for each person
    private String fullName;            // Person's full name
    private Gender gender;              // Person's gender
    private int birthYear;              // Birth year
    private Integer deathYear;          // Death year (nullable)

    private final List<Person> parents = new ArrayList<>();  // Max 2 parents
    private final List<Person> children = new ArrayList<>(); // Can have many children
    private Person spouse;             // Current spouse
    private Integer marriageYear;      // Year married
    private Integer divorceYear;       // Year divorced (nullable)

    // --- Constructor ---
    public Person(String id, String fullName, Gender gender, int birthYear, Integer deathYear) {
        this.id = id;
        setFullName(fullName);  // use setter to validate
        setGender(gender);      // use setter to validate
        setBirthYear(birthYear);
        setDeathYear(deathYear);
    }

    // --- Getters ---
    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public Gender getGender() { return gender; }
    public int getBirthYear() { return birthYear; }
    public Integer getDeathYear() { return deathYear; }
    public List<Person> getParents() { return parents; }
    public List<Person> getChildren() { return children; }
    public Person getSpouse() { return spouse; }
    public Integer getMarriageYear() { return marriageYear; }
    public Integer getDivorceYear() { return divorceYear; }

    // --- Setters with validation ---
    public void setFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full name cannot be blank");
        }
        this.fullName = fullName;
    }

    public void setGender(Gender gender) {
        if (gender == null) throw new IllegalArgumentException("Gender cannot be null");
        this.gender = gender;
    }

    public void setBirthYear(int birthYear) {
        if (birthYear < 1000 || birthYear > 2100) {
            throw new IllegalArgumentException("Invalid birth year");
        }
        this.birthYear = birthYear;
    }

    public void setDeathYear(Integer deathYear) {
        if (deathYear != null && deathYear < birthYear) {
            throw new IllegalArgumentException("Death year cannot be before birth year");
        }
        this.deathYear = deathYear;
    }

    // --- Computed Methods ---
    public boolean isAlive() {
        return deathYear == null;
    }

    public int ageIn(int year) {
        if (year < birthYear) return 0;
        int endYear = (deathYear != null && deathYear < year) ? deathYear : year;
        return endYear - birthYear;
    }

    // --- Relationship Methods ---
    public void addParent(Person parent) {
        if (parents.size() >= 2) throw new IllegalArgumentException("A person can have at most 2 parents");
        if (parent == this) throw new IllegalArgumentException("Cannot be own parent");
        parents.add(parent);
    }

    public void addChild(Person child) {
        children.add(child);
    }

    public void marry(Person partner, int year) {
        if (this.spouse != null) throw new IllegalArgumentException("Already married");
        this.spouse = partner;
        this.marriageYear = year;
    }

    public void setDivorceYear(int year) {
        if (this.spouse == null) throw new IllegalArgumentException("Not married");
        this.divorceYear = year;
    }

    // --- Display ---
    @Override
    public String toString() {
        return id + " | " + fullName + " | " + gender + " | b." + birthYear +
                (deathYear != null ? " d." + deathYear : "");
    }
}
