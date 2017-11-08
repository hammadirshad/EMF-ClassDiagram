package org.eclipse.emf.example.models._sequence;

import org.eclipse.emf.example.models._class.ClassStructure;

import java.util.ArrayList;

public class SequenceDiagram {
    private ArrayList<SequenceLifeline> lifelines = new ArrayList<>();
    private ArrayList<SequenceMessage> messages = new ArrayList<>();
    private ArrayList<ClassStructure> classes = new ArrayList<>();
    private ArrayList<SequenceAttribute> attributes = new ArrayList<>();
    private ArrayList<SequenceGate> gates = new ArrayList<>();
    private ArrayList<SequenceBehavior> behaviors = new ArrayList<>();
    private ArrayList<SequenceCombinedFragment> fragments = new ArrayList<>();

    public ArrayList<SequenceLifeline> getLifelines() {
        return lifelines;
    }

    public void setLifelines(ArrayList<SequenceLifeline> lifelines) {
        this.lifelines = lifelines;
    }

    public ArrayList<SequenceMessage> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<SequenceMessage> messages) {
        this.messages = messages;
    }

    public ArrayList<ClassStructure> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<ClassStructure> classes) {
        this.classes = classes;
    }

    public ArrayList<SequenceAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<SequenceAttribute> attributes) {
        this.attributes = attributes;
    }

    public void addMessage(SequenceMessage message) {
        this.messages.add(message);
    }

    public ArrayList<SequenceGate> getGates() {
        return gates;
    }

    public void setGates(ArrayList<SequenceGate> gates) {
        this.gates = gates;
    }

    public ArrayList<SequenceBehavior> getBehaviors() {
        return behaviors;
    }

    public void setBehaviors(ArrayList<SequenceBehavior> behaviors) {
        this.behaviors = behaviors;
    }

    public ArrayList<SequenceCombinedFragment> getFragments() {
        return fragments;
    }

    public void setFragments(ArrayList<SequenceCombinedFragment> fragments) {
        this.fragments = fragments;
    }

    public void addLifeline(SequenceLifeline lifeLine) {
        this.lifelines.add(lifeLine);
    }

    public void addClass(ClassStructure structure) {
        this.classes.add(structure);
    }

    public void addAttribute(SequenceAttribute attribute) {
        this.attributes.add(attribute);
    }

    public void addGate(SequenceGate gate) {
        this.gates.add(gate);
    }

    public void addBehavior(SequenceBehavior behavior) {
        this.behaviors.add(behavior);
    }

    public void addFragment(SequenceCombinedFragment fragment) {
        this.fragments.add(fragment);
    }
}
