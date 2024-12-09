package by.solution.view;

import by.solution.controller.MapController;
import by.solution.model.MapModel;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

class RoundedButton extends JButton {

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(g);
        g2.dispose();
    }
}

public class View extends JFrame {

    private final JList<MapModel.Entry<String, String>> list1;
    private final JList<MapModel.Entry<String, String>> list2;
    private final JList<MapModel.Entry<String, String>> resultList;

    private final MapModel<String, String> map1;
    private final MapModel<String, String> map2;
    private final JTextField keyToAddTextField;
    private final JTextField valueToAddTextField;

    private final JButton addButton;
    private final JButton clearButton;
    private final JButton removeButton;

    private final JButton uniteButton;
    private final JButton intersectButton;
    private final JButton differenceABButton;

    private final JRadioButton mapAButton;
    private final JRadioButton mapBButton;
    private final ButtonGroup mapGroup;

    public View(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Font largerFont = new Font("Dialog", Font.BOLD, 16);
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("RadioButton.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

        map1 = new MapModel<>();
        map2 = new MapModel<>();

        list1 = map1.getJList();
        list2 = map2.getJList();
        resultList = new JList<>(new DefaultListModel<>());

        keyToAddTextField = createTextField("key");
        valueToAddTextField = createTextField("value");

        addButton = createButton("Add Element", false);
        clearButton = createButton("Clear", false);
        removeButton = createButton("Remove Element", false);

        uniteButton = createButton("Unite Maps", true);
        intersectButton = createButton("Intersect Maps", true);
        differenceABButton = createButton("Find Difference of Maps (A-B)", true);

        mapAButton = new JRadioButton("Map A");
        mapBButton = new JRadioButton("Map B");
        mapGroup = new ButtonGroup();
        mapGroup.add(mapAButton);
        mapGroup.add(mapBButton);

        JPanel topPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        topPanel.add(new JLabel("List A"));
        topPanel.add(new JLabel("List B"));
        topPanel.add(new JLabel("Result List"));
        topPanel.add(new JScrollPane(list1));
        topPanel.add(new JScrollPane(list2));
        topPanel.add(new JScrollPane(resultList));

        JPanel middlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponent(middlePanel, keyToAddTextField, gbc, 0, 0, 2);
        addComponent(middlePanel, valueToAddTextField, gbc, 0, 1, 2);
        addComponent(middlePanel, addButton, gbc, 0, 2, 1);
        addComponent(middlePanel, clearButton, gbc, 1, 2, 1);
        addComponent(middlePanel, removeButton, gbc, 0, 3, 2);

        JPanel bottomPanel = new JPanel(new GridBagLayout());
        addComponent(bottomPanel, mapAButton, gbc, 0, 0, 1);
        addComponent(bottomPanel, mapBButton, gbc, 1, 0, 1);
        addComponent(bottomPanel, uniteButton, gbc, 0, 1, 2);
        addComponent(bottomPanel, intersectButton, gbc, 0, 2, 2);
        addComponent(bottomPanel, differenceABButton, gbc, 0, 3, 2);

        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        new MapController(map1, map2, this);
    }

    private JTextField createTextField(String placeholder) {
        JTextField textField = new JTextField(placeholder);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
        return textField;
    }

    private JButton createButton(String text, boolean enabled) {
        RoundedButton button = new RoundedButton(text);
        button.setEnabled(enabled);
        button.setBackground(new Color(70, 130, 180)); // Set your desired color
        button.setForeground(Color.WHITE); // Set text color
        return button;
    }

    private void addComponent(JPanel panel, Component component, GridBagConstraints gbc, int x, int y, int width) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        panel.add(component, gbc);
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getUniteButton() {
        return uniteButton;
    }

    public JButton getIntersectButton() {
        return intersectButton;
    }

    public JButton getDifferenceABButton() {
        return differenceABButton;
    }

    public JTextField getKeyToAddTextField() {
        return keyToAddTextField;
    }

    public JTextField getValueToAddTextField() {
        return valueToAddTextField;
    }

    public JList<MapModel.Entry<String, String>> getResultList() {
        return resultList;
    }

    public JList<MapModel.Entry<String, String>> getFirstList() {
        return list1;
    }

    public JList<MapModel.Entry<String, String>> getSecondList() {
        return list2;
    }

    public boolean isMapASelected() {
        return mapAButton.isSelected();
    }

    public void updateMapDisplay(MapModel<String, String> map) {
        if (map == map1) {
            list1.setModel(map.getJList().getModel());
        } else if (map == map2) {
            list2.setModel(map.getJList().getModel());
        } else {
            resultList.setModel(map.getJList().getModel());
        }
    }

    public boolean isMapBSelected() {
        return mapBButton.isSelected();
    }
}