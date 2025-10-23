
package com.example.familytree.cli;

import com.example.familytreea.model.*;
import com.example.familytreea.factory.*;
import com.example.familytreea.renderer.*;
import com.example.familytreea.strategy.*;
import com.example.familytreea.utility.*;


import model.*;
import factory.*;
import renderer.*;
import strategy.*;

import java.util.List;
import java.util.Scanner;

/**
 * CLI class to handle user input and execute commands.
 */
public class Cli {

    private final FamilyTree familyTree = new FamilyTree();
    private final Scanner scanner = new Scanner(System.in);

    private TraversalStrategy dfs = new DFSTraversal();
    private Renderer indentedRenderer = new IndentedTreeRenderer();
    private Renderer lineRenderer = new LineRenderer();

    public void start() {
        System.out.println("Family Tree CLI is running...");
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("EXIT")) break;

            try {
                handleCommand(line);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void handleCommand(String line) {
        String[] parts = line.split(" ");
        String command = parts[0].toUpperCase();

        switch (command) {
            case "ADD_PERSON" -> addPersonCommand(parts);
            case "ADD_PARENT_CHILD" -> addParentChildCommand(parts);
            case "MARRY" -> marryCommand(parts);
            case "ANCESTORS" -> ancestorsCommand(parts);
            case "DESCENDANTS" -> descendantsCommand(parts);
            case "SIBLINGS" -> siblingsCommand(parts);
            case "SHOW" -> showCommand(parts);
            default -> System.out.println("Unknown command: " + command);
        }
    }

    private void addPersonCommand(String[] parts) {
        if (parts.length < 4) {
            System.out.println("Usage: ADD_PERSON \"Full Name\" <Gender> <BirthYear> [DeathYear]");
            return;
        }
        String fullName = parts[1].replace("\"", "");
        Gender gender = Gender.valueOf(parts[2].toUpperCase());
        int birthYear = Integer.parseInt(parts[3]);
        Integer deathYear = parts.length >= 5 ? Integer.parseInt(parts[4]) : null;

        String id = IdGenerator.getInstance().nextId();
        Person person = PersonFactory.createPerson(id, fullName, gender, birthYear, deathYear);
        familyTree.addPerson(person);
        System.out.println("Added: " + id);
    }

    private void addParentChildCommand(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Usage: ADD_PARENT_CHILD <parentId> <childId>");
            return;
        }
        familyTree.addParentChild(parts[1], parts[2]);
        System.out.println("Parent-child link added.");
    }

    private void marryCommand(String[] parts) {
        if (parts.length < 4) {
            System.out.println("Usage: MARRY <personAId> <personBId> <Year>");
            return;
        }
        int year = Integer.parseInt(parts[3]);
        familyTree.marry(parts[1], parts[2], year);
        System.out.println("Marriage recorded.");
    }

    private void ancestorsCommand(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Usage: ANCESTORS <personId> <generations>");
            return;
        }
        String id = parts[1];
        int gen = Integer.parseInt(parts[2]);
        List<Person> ancestors = familyTree.ancestorsOf(id, gen);
        indentedRenderer.render(familyTree.getPerson(id), gen);
    }

    private void descendantsCommand(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Usage: DESCENDANTS <personId> <generations>");
            return;
        }
        String id = parts[1];
        int gen = Integer.parseInt(parts[2]);
        List<Person> descendants = familyTree.descendantsOf(id, gen);
        indentedRenderer.render(familyTree.getPerson(id), gen);
    }

    private void siblingsCommand(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Usage: SIBLINGS <personId>");
            return;
        }
        List<Person> siblings = familyTree.siblingsOf(parts[1]);
        if (siblings.isEmpty()) {
            System.out.println("<none>");
        } else {
            for (Person s : siblings) {
                System.out.println(s.toString());
            }
        }
    }

    private void showCommand(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Usage: SHOW <personId>");
            return;
        }
        Person p = familyTree.getPerson(parts[1]);
        StringBuilder sb = new StringBuilder();
        sb.append(p.getId()).append(" | ")
                .append(p.getFullName()).append(" | ")
                .append(p.getGender()).append(" | b.")
                .append(p.getBirthYear());
        if (p.getDeathYear() != null) sb.append(" d.").append(p.getDeathYear());
        sb.append(" | spouse=").append(p.getSpouse() != null ? p.getSpouse().getId() : "none");
        sb.append(" | children=").append(p.getChildren().size());
        System.out.println(sb.toString());
    }
}
