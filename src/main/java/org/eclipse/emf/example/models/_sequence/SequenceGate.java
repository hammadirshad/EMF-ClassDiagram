package org.eclipse.emf.example.models._sequence;

public class SequenceGate {
    private String gateMessage;
    private SequenceLifeline gateLifeline;

    public SequenceGate() {
        super();
    }

    public SequenceGate(String gateMessage, SequenceLifeline gateLifeline) {
        super();
        this.gateMessage = gateMessage;
        this.gateLifeline = gateLifeline;
    }

    public String getGateMessage() {
        return gateMessage;
    }

    public void setGateMessage(String gateMessage) {
        this.gateMessage = gateMessage;
    }

    public SequenceLifeline getGateLifeline() {
        return gateLifeline;
    }

    public void setGateLifeline(SequenceLifeline gateLifeline) {
        this.gateLifeline = gateLifeline;
    }

}
