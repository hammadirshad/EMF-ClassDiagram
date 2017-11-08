package org.eclipse.emf.example.models._sequence;

import java.util.ArrayList;

public class SequenceBehavior {
    private SequenceLifeline lifeline;
    private SequenceMessage start;
    private SequenceMessage finish;
    private ArrayList<SequenceMessage> calls = new ArrayList<SequenceMessage>();
    private ArrayList<SequenceCombinedFragment> fragments = new ArrayList<SequenceCombinedFragment>();

    public SequenceLifeline getLifeline() {
        return lifeline;
    }

    public void setLifeline(SequenceLifeline lifeline) {
        this.lifeline = lifeline;
    }

    public SequenceMessage getStart() {
        return start;
    }

    public void setStart(SequenceMessage start) {
        this.start = start;
    }

    public SequenceMessage getFinish() {
        return finish;
    }

    public void setFinish(SequenceMessage finish) {
        this.finish = finish;
    }

    public ArrayList<SequenceMessage> getCalls() {
        return calls;
    }

    public void setCalls(ArrayList<SequenceMessage> calls) {
        this.calls = calls;
    }

    public void addCall(SequenceMessage call) {
        this.calls.add(call);
    }

    public ArrayList<SequenceCombinedFragment> getFragments() {
        return fragments;
    }

    public void setFragments(ArrayList<SequenceCombinedFragment> fragments) {
        this.fragments = fragments;
    }

    public void addFragment(SequenceCombinedFragment fragment) {
        this.fragments.add(fragment);
    }

}
