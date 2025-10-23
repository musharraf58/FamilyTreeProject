package com.example.familytree.strategy;


import java.util.*;

/**
 * Core class to manage all people and relationships in the family tree.
 */
public final class FamilyTree {

    private final Map<String, Person> people = new HashMap<>();

    // --- Add a person to the tree ---
    public void addPerson(Person person) {
        if (people.containsKey(person.getId())) {
            throw new IllegalArgumentException("Person with ID already exists: " + person.getId());
        }
        people.put(person.getId(), person);
    }

    // --- Get a person by ID ---
    public Person getPerson(String id) {
        Person p = people.get(id);
        if (p == null) throw new IllegalArgumentException("No person found with ID: " + id);
        return p;
    }

    // --- Link parent-child relationship ---
    public void addParentChild(String parentId, String childId) {
        Person parent = getPerson(parentId);
        Person child = getPerson(childId);

        // Prevent cycles: child cannot be parent of ancestor
        if (isAncestor(child, parent)) {
            throw new IllegalArgumentException("Cannot create cycle: " + child.getFullName() + " is an ancestor of " + parent.getFullName());
        }

        child.addParent(parent);
        parent.addChild(child);
    }

    // --- Link spouses ---
    public void marry(String personAId, String personBId, int year) {
        Person a = getPerson(personAId);
        Person b = getPerson(personBId);

        if (a.getSpouse() != null || b.getSpouse() != null) {
            throw new IllegalArgumentException("One of the persons is already married");
        }

        a.marry(b, year);
        b.marry(a, year);
    }

    // --- Siblings (share at least one parent, exclude self) ---
    public List<Person> siblingsOf(String personId) {
        Person p = getPerson(personId);
        Set<Person> siblings = new HashSet<>();
        for (Person parent : p.getParents()) {
            siblings.addAll(parent.getChildren());
        }
        siblings.remove(p);
        return new ArrayList<>(siblings);
    }

    // --- Children of a person ---
    public List<Person> childrenOf(String personId) {
        return new ArrayList<>(getPerson(personId).getChildren());
    }

    // --- Spouse of a person ---
    public Person spouseOf(String personId) {
        return getPerson(personId).getSpouse();
    }

    // --- Ancestors (0 = self, 1 = parents, 2 = grandparents, etc.) ---
    public List<Person> ancestorsOf(String personId, int generations) {
        List<Person> result = new ArrayList<>();
        Person p = getPerson(personId);
        ancestorsHelper(p, generations, 0, result);
        return result;
    }

    private void ancestorsHelper(Person p, int maxGen, int currentGen, List<Person> list) {
        if (currentGen > maxGen) return;
        list.add(p);
        for (Person parent : p.getParents()) {
            ancestorsHelper(parent, maxGen, currentGen + 1, list);
        }
    }

    // --- Descendants (0 = self, 1 = children, 2 = grandchildren, etc.) ---
    public List<Person> descendantsOf(String personId, int generations) {
        List<Person> result = new ArrayList<>();
        Person p = getPerson(personId);
        descendantsHelper(p, generations, 0, result);
        return result;
    }

    private void descendantsHelper(Person p, int maxGen, int currentGen, List<Person> list) {
        if (currentGen > maxGen) return;
        list.add(p);
        for (Person child : p.getChildren()) {
            descendantsHelper(child, maxGen, currentGen + 1, list);
        }
    }

    // --- Helper to detect cycles ---
    private boolean isAncestor(Person descendant, Person ancestor) {
        if (ancestor.getChildren().contains(descendant)) return true;
        for (Person child : ancestor.getChildren()) {
            if (isAncestor(descendant, child)) return true;
        }
        return false;
    }
}
