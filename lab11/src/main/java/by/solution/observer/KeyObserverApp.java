package by.solution.observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyObserverApp extends JFrame {
    public KeyObserverApp(String str) {
        super(str);

        KeySubject keySubject = new KeySubject();

        LargeKeyDisplay largeKeyDisplay = new LargeKeyDisplay();
        KeyLogDisplay keyLogDisplay = new KeyLogDisplay();

        keySubject.attach(largeKeyDisplay);
        keySubject.attach(keyLogDisplay);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLayout(new BorderLayout());

        add(largeKeyDisplay, BorderLayout.NORTH);
        add(new JScrollPane(keyLogDisplay), BorderLayout.CENTER);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    String keyText = KeyEvent.getKeyText(e.getKeyCode());
                    keySubject.setKey(keyText);
                    keySubject.notifyObservers();
                }
                return false;
            }
        });

        setVisible(true);
    }
}