package by.solution.observer;

import javax.swing.*;

public class KeyLogDisplay extends JScrollPane implements Observer {
    private final DefaultListModel<String> logModel;
    private final JList<String> logList;

    public KeyLogDisplay() {
        logModel = new DefaultListModel<>();
        logList = new JList<>(logModel);
        setViewportView(logList);
    }

    @Override
    public void update(String key) {
        logModel.addElement(key); // Добавляем новую запись в список
        logList.ensureIndexIsVisible(logModel.getSize() - 1); // Прокручиваем к последнему элементу
    }
}
