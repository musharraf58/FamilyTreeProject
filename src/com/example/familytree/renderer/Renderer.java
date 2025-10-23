package com.example.familytree.renderer;


import model.Person;

/**
 * Interface for rendering a family tree or a list of people.
 */
public interface Renderer {
    /**
     * Render a list of persons starting from root.
     * @param root starting person
     * @param generations how many generations to display
     */
    void render(Person root, int generations);
}
