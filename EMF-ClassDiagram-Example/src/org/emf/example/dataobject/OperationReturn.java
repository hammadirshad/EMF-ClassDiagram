package org.emf.example.dataobject;

public class OperationReturn {
    private String type = "OclVoid";
    private boolean Class;
    private boolean collection;


    public OperationReturn() {

    }

    public OperationReturn(String type, boolean class1, boolean collection) {
        super();
        this.type = type;
        Class = class1;
        this.collection = collection;
    }

    public String getType() {
        return type;
    }

    public void setType(String attributeType) {
        this.type = attributeType;
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
