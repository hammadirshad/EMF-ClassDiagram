package org.eclipse.emf.example.models._statemachine;

import java.util.ArrayList;

public class StateMachine {

    private String name;
    private ArrayList<TransitionDetails> transitions;
    private String type;

    public StateMachine() {
    }


    public StateMachine(String name, ArrayList<TransitionDetails> transitions, String type) {
        this.name = name;
        this.transitions = transitions;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<TransitionDetails> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<TransitionDetails> transitions) {
        this.transitions = transitions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
