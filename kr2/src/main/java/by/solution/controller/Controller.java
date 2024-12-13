package by.solution.controller;

import by.solution.model.TreeList;
import by.solution.strategy.DefaultMaxStrategy;
import by.solution.strategy.StreamApiMaxStrategy;
import by.solution.strategy.Strategy;
import by.solution.view.View;
import by.solution.visitor.MaxPathTreeListVisitor;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private final TreeList<Integer> tree;
    private final View view;
    private Strategy<Integer> maxStrategy;

    public Controller(TreeList<Integer> tree, View view) {
        this.tree = tree;
        this.view = view;
        this.maxStrategy = new DefaultMaxStrategy<>(); // Default strategy
        initController();
    }

    private void initController() {
        this.view.getAddButton().addActionListener(e -> addElementToTree());
        this.view.getOpenMenuItem().addActionListener(e -> openFile());
        this.view.getDisplayArrayMenuItem().addActionListener(e -> displayTreeAsArray());
        this.view.getDisplayMaxMenuItem().addActionListener(e -> displayMaxElement());
        this.view.getDisplayMaxPathMenuItem().addActionListener(e -> displayPathToMax());
        this.view.getDisplayPreOrderMenuItem().addActionListener(e -> displayPreOrderTraversal());
        this.view.getDefaultMaxStrategyMenuItem().addActionListener(e -> setDefaultMaxStrategy());
        this.view.getStreamApiMaxStrategyMenuItem().addActionListener(e -> setStreamApiMaxStrategy());
    }

    private void addElementToTree() {
        String valueStr = JOptionPane.showInputDialog(view, "Enter an integer value:");
        if (valueStr != null && !valueStr.trim().isEmpty()) {
            try {
                int value = Integer.parseInt(valueStr.trim());
                tree.add(value);
                this.view.updateTreeDisplay(tree.toJList());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid input. Please enter an integer.");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Input cannot be empty.");
        }
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            loadTreeFromFile(selectedFile.getAbsolutePath());
        }
    }

    private void loadTreeFromFile(String filePath) {
        if (filePath != null && !filePath.trim().isEmpty()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath.trim()))) {
                String line;
                tree.clear();
                while ((line = reader.readLine()) != null) {
                    try {
                        int value = Integer.parseInt(line.trim());
                        tree.add(value);
                    } catch (NumberFormatException ex) {
                        // Ignore invalid lines
                    }
                }
                this.view.updateTreeDisplay(tree.toJList());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(view, "Error reading file: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(view, "File path cannot be empty.");
        }
    }

    private void displayTreeAsArray() {
        List<Integer> elements = tree.getList();
        this.view.updateArrayDisplay(elements.toString());
    }

    private void displayMaxElement() {
        Integer maxElement = maxStrategy.process(tree);
        this.view.updateMaxDisplay(maxElement.toString());
    }

    private void displayPathToMax() {
        MaxPathTreeListVisitor<Integer> maxPathVisitor = new MaxPathTreeListVisitor<>(tree);
        tree.accept(maxPathVisitor);
        List<Integer> path = maxPathVisitor.moveToMax();
        String pathString = path.stream().map(String::valueOf).collect(Collectors.joining(" - "));
        this.view.updatePathDisplay(pathString);
    }

    private void displayPreOrderTraversal() {
        List<Integer> preOrder = tree.preOrderTraversal();
        this.view.updatePreOrderDisplay(preOrder.toString());
    }

    private void setDefaultMaxStrategy() {
        this.maxStrategy = new DefaultMaxStrategy<>();
        JOptionPane.showMessageDialog(view, "Max strategy set to Default Strategy.");
    }

    private void setStreamApiMaxStrategy() {
        this.maxStrategy = new StreamApiMaxStrategy<>();
        JOptionPane.showMessageDialog(view, "Max strategy set to Stream API Strategy.");
    }
}