package org.emf.example.dataobject;

public class MethodReturn {
    private String attributeType = "OclVoid";
    private boolean Class;
    private boolean collection;


    public MethodReturn() {

    }

    public MethodReturn(String attributeType, boolean class1, boolean collection) {
        super();
        this.attributeType = attributeType;
        Class = class1;
        this.collection = collection;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    public boolean isClass() {
        return Class;
    }

    public void setClass(boolean class1) {
        Class = class1;
    }

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }


}
