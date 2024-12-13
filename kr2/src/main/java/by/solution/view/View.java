package by.solution.view;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private final JMenuItem addButton;
    private final JTextArea treeArea;
    private final JTextArea resultArea;
    private final JMenuItem openMenuItem;
    private final JMenuItem displayArrayMenuItem;
    private final JMenuItem displayMaxMenuItem;
    private final JMenuItem displayMaxPathMenuItem;
    private final JMenuItem displayPreOrderMenuItem;
    private final JMenuItem defaultMaxStrategyMenuItem;
    private final JMenuItem streamApiMaxStrategyMenuItem;

    public View(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        openMenuItem = new JMenuItem("Open");
        fileMenu.add(openMenuItem);
        menuBar.add(fileMenu);

        JMenu viewMenu = new JMenu("View");
        displayArrayMenuItem = new JMenuItem("Display Array");
        displayMaxMenuItem = new JMenuItem("Display Max");
        displayMaxPathMenuItem = new JMenuItem("Display Max Path");
        displayPreOrderMenuItem = new JMenuItem("Display Pre-Order");
        viewMenu.add(displayArrayMenuItem);
        viewMenu.add(displayMaxMenuItem);
        viewMenu.add(displayMaxPathMenuItem);
        viewMenu.add(displayPreOrderMenuItem);
        menuBar.add(viewMenu);

        JMenu editMenu = new JMenu("Edit");
        addButton = new JMenuItem("Add Element");
        editMenu.add(addButton);
        menuBar.add(editMenu);

        JMenu strategyMenu = new JMenu("Max Strategy");
        defaultMaxStrategyMenuItem = new JMenuItem("Default Strategy");
        streamApiMaxStrategyMenuItem = new JMenuItem("Stream API Strategy");
        strategyMenu.add(defaultMaxStrategyMenuItem);
        strategyMenu.add(streamApiMaxStrategyMenuItem);
        menuBar.add(strategyMenu);

        setJMenuBar(menuBar);

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        treeArea = new JTextArea();
        treeArea.setEditable(false);
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        textPanel.add(new JScrollPane(treeArea));
        textPanel.add(new JScrollPane(resultArea));

        add(textPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    public JMenuItem getAddButton() {
        return addButton;
    }

    public JMenuItem getOpenMenuItem() {
        return openMenuItem;
    }

    public JMenuItem getDisplayArrayMenuItem() {
        return displayArrayMenuItem;
    }

    public JMenuItem getDisplayMaxMenuItem() {
        return displayMaxMenuItem;
    }

    public JMenuItem getDisplayMaxPathMenuItem() {
        return displayMaxPathMenuItem;
    }

    public JMenuItem getDisplayPreOrderMenuItem() {
        return displayPreOrderMenuItem;
    }

    public JMenuItem getDefaultMaxStrategyMenuItem() {
        return defaultMaxStrategyMenuItem;
    }

    public JMenuItem getStreamApiMaxStrategyMenuItem() {
        return streamApiMaxStrategyMenuItem;
    }

    public void updateTreeDisplay(JList<?> treeList) {
        treeArea.setText("");
        for (int i = 0; i < treeList.getModel().getSize(); i++) {
            treeArea.append(treeList.getModel().getElementAt(i).toString() + "\n");
        }
    }

    public void updateArrayDisplay(String array) {
        resultArea.setText(array);
    }

    public void updateMaxDisplay(String max) {
        resultArea.setText("Max Element: " + max);
    }

    public void updatePathDisplay(String path) {
        resultArea.setText("Path to Max: " + path);
    }

    public void updatePreOrderDisplay(String preOrder) {
        resultArea.setText("Pre-Order Traversal: " + preOrder);
    }
}