package by.solution.controller;

import by.solution.model.MapModel;
import by.solution.view.View;
import by.solution.visitor.DifferenceVisitor;
import by.solution.visitor.IntersectionVisitor;
import by.solution.visitor.UnionVisitor;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.List;

public class MapController {
    private final MapModel<String, String> map1;
    private final MapModel<String, String> map2;
    private final View view;

    public MapController(MapModel<String, String> map1, MapModel<String, String> map2, View view) {
        this.map1 = map1;
        this.map2 = map2;
        this.view = view;
        initController();
    }

    private void initController() {
        this.view.getAddButton().addActionListener(e -> addElementsToMap());

        this.view.getClearButton().addActionListener(e -> clearMap());

        this.view.getRemoveButton().addActionListener(e -> removeSelectedElementsFromMap());

        this.view.getUniteButton().addActionListener(e -> {
            UnionVisitor<String, String> unionVisitor = new UnionVisitor<>(map1);
            map2.accept(unionVisitor);
            this.view.updateMapDisplay(unionVisitor.getResult());
        });

        this.view.getIntersectButton().addActionListener(e -> {
            IntersectionVisitor<String, String> intersectionVisitor = new IntersectionVisitor<>(map1);
            map2.accept(intersectionVisitor);
            this.view.updateMapDisplay(intersectionVisitor.getResult());
        });

        this.view.getDifferenceABButton().addActionListener(e -> {
            DifferenceVisitor<String, String> differenceVisitor = new DifferenceVisitor<>(map1);
            map2.accept(differenceVisitor);
            this.view.updateMapDisplay(differenceVisitor.getResult());
        });

        this.view.getFirstList().addListSelectionListener(e -> updateRemoveButtonState());
        this.view.getSecondList().addListSelectionListener(e -> updateRemoveButtonState());

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                toggleButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                toggleButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                toggleButtonState();
            }

            private void toggleButtonState() {
                boolean isKeyFieldEmpty = view.getKeyToAddTextField().getText().trim().isEmpty() || view.getKeyToAddTextField().getText().equals("key");
                boolean isValueFieldEmpty = view.getValueToAddTextField().getText().trim().isEmpty() || view.getValueToAddTextField().getText().equals("value");
                view.getAddButton().setEnabled(!isKeyFieldEmpty && !isValueFieldEmpty);
            }
        };

        this.view.getKeyToAddTextField().getDocument().addDocumentListener(documentListener);
        this.view.getValueToAddTextField().getDocument().addDocumentListener(documentListener);
    }

    private void addElementsToMap() {
        String key = this.view.getKeyToAddTextField().getText().trim();
        String value = this.view.getValueToAddTextField().getText().trim();
        if (key.equals("key") || value.equals("value")) {
            return;
        }
        MapModel<String, String> selectedMap = view.isMapASelected() ? map1 : map2;
        selectedMap.put(key, value);
        this.view.updateMapDisplay(selectedMap);
        this.view.getClearButton().setEnabled(true);
        updateClearButtonState();
    }

    private void clearMap() {
        MapModel<String, String> selectedMap = view.isMapASelected() ? map1 : map2;
        selectedMap.clear();
        this.view.updateMapDisplay(selectedMap);
        updateClearButtonState();
    }

    private void removeSelectedElementsFromMap() {
        if (!view.isMapASelected() && !view.isMapBSelected()) {
            return;
        }
        MapModel<String, String> selectedMap = view.isMapASelected() ? map1 : map2;
        JList<MapModel.Entry<String, String>> selectedList = view.isMapASelected() ? view.getFirstList() : view.getSecondList();
        List<MapModel.Entry<String, String>> selectedValues = selectedList.getSelectedValuesList();
        for (MapModel.Entry<String, String> entry : selectedValues) {
            selectedMap.remove(entry.getKey());
        }
        this.view.updateMapDisplay(selectedMap);
        updateClearButtonState();
    }

    private void updateClearButtonState() {
        MapModel<String, String> selectedMap = view.isMapASelected() ? map1 : map2;
        this.view.getClearButton().setEnabled(!selectedMap.isEmpty());
    }

    private void updateRemoveButtonState() {
        boolean isAnyFieldSelected = !this.view.getFirstList().isSelectionEmpty() || !this.view.getSecondList().isSelectionEmpty();
        this.view.getRemoveButton().setEnabled(isAnyFieldSelected);
    }
}