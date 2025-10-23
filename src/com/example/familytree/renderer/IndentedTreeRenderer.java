package com.example.familytree.renderer;


import model.Person;

/**
 * Renders the family tree as an indented hierarchy.
 */
public class IndentedTreeRenderer implements Renderer {

    @Override
    public void render(Person root, int generations) {
        renderHelper(root, generations, 0);
    }

    private void renderHelper(Person person, int maxGen, int level) {
        if (level > maxGen) return;

        // Indent according to level
        System.out.println("  ".repeat(level) + "- " + person.toString());

        for (Person child : person.getChildren()) {
            renderHelper(child, maxGen, level + 1);
        }
    }
}
