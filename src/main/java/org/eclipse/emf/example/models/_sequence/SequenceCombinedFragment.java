package org.eclipse.emf.example.models._sequence;

import java.util.ArrayList;

public class SequenceCombinedFragment {
    private String condition = null;
    private String operation = null;
    private ArrayList<SequenceLifeline> sequenceLifelines = new ArrayList<SequenceLifeline>();
    private ArrayList<SequenceMessage> calls = new ArrayList<SequenceMessage>();

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public ArrayList<SequenceLifeline> getSequenceLifelines() {
        return sequenceLifelines;
    }

    public void setSequenceLifelines(
            ArrayList<SequenceLifeline> sequenceLifelines) {
        this.sequenceLifelines = sequenceLifelines;
    }

    public void addSequenceLifeline(SequenceLifeline sequenceLifeline) {
        this.sequenceLifelines.add(sequenceLifeline);
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

}
