package org.eclipse.emf.example.models._sequence;

import org.eclipse.emf.example.models._class.ClassStructure;

import java.util.ArrayList;
import java.util.List;

public class SequenceDiagram {
    private List<SequenceLifeline> lifelines = new ArrayList<>();
    private List<SequenceMessage> messages = new ArrayList<>();
    private List<ClassStructure> classes = new ArrayList<>();
    private List<SequenceAttribute> attributes = new ArrayList<>();
    private List<SequenceGate> gates = new ArrayList<>();
    private List<SequenceBehavior> behaviors = new ArrayList<>();
    private List<SequenceCombinedFragment> fragments = new ArrayList<>();

    public List<SequenceLifeline> getLifelines() {
        return lifelines;
    }

    public void setLifelines(List<SequenceLifeline> lifelines) {
        this.lifelines = lifelines;
    }

    public List<SequenceMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<SequenceMessage> messages) {
        this.messages = messages;
    }

    public List<ClassStructure> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassStructure> classes) {
        this.classes = classes;
    }

    public List<SequenceAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<SequenceAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<SequenceGate> getGates() {
        return gates;
    }

    public void setGates(List<SequenceGate> gates) {
        this.gates = gates;
    }

    public List<SequenceBehavior> getBehaviors() {
        return behaviors;
    }

    public void setBehaviors(List<SequenceBehavior> behaviors) {
        this.behaviors = behaviors;
    }

    public List<SequenceCombinedFragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<SequenceCombinedFragment> fragments) {
        this.fragments = fragments;
    }
}
