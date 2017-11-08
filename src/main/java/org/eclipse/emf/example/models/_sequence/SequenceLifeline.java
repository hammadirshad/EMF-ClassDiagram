package org.eclipse.emf.example.models._sequence;

public class SequenceLifeline {

    private String lifelineName;
    private String represents;

    public SequenceLifeline() {
    }

    public SequenceLifeline(String lifelineName, String represents) {
        super();
        this.lifelineName = lifelineName;
        this.represents = represents;
    }

    public String getLifelineName() {
        return lifelineName;
    }

    public void setLifelineName(String lifelineName) {
        this.lifelineName = lifelineName;
    }

    public String getRepresents() {
        return represents;
    }

    public void setRepresents(String represents) {
        this.represents = represents;
    }

}
