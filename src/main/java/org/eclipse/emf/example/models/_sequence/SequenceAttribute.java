package org.eclipse.emf.example.models._sequence;

public class SequenceAttribute {
    private String attributeName;
    private String attributeType;

    public SequenceAttribute() {
        super();

    }

    public SequenceAttribute(String attributeName, String attributeType) {
        super();
        this.attributeName = attributeName;
        this.attributeType = attributeType;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

}
