package com.example.familytree.renderer;


import model.Person;

/**
 * Renders each person in a single line (compact view).
 */
public class LineRenderer implements Renderer {

    @Override
    public void render(Person root, int generations) {
        renderHelper(root, generations, 0);
    }

    private void renderHelper(Person person, int maxGen, int level) {
        if (level > maxGen) return;

        // Print one line per person
        System.out.println(person.toString());

        for (Person child : person.getChildren()) {
            renderHelper(child, maxGen, level + 1);
        }
    }
}
