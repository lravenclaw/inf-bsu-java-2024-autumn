package by.solution.example;

import by.solution.pair.PairElementContainer;

import javax.swing.*;

public class SetView extends JTextArea {
    private PairElementContainer<String, String> model;

    SetView(PairElementContainer<String,String> model){
        this.model = model;
        update();
    }

    public void update(){
        setText(model.toString());
    }
}
