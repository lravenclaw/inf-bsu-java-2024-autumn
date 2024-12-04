package by.solution.observer;

import javax.swing.*;

public class LargeKeyDisplay extends JLabel implements Observer {
    public LargeKeyDisplay() {
        super("Press a key", SwingConstants.CENTER);
        setFont(getFont().deriveFont(48f));
    }

    @Override
    public void update(String key) {
        setText(key);
    }
}

