package org.eclipse.emf.example.models._class;

public class InstanceAttribute {
    private String name;
    private String type;
    private Object values[];
    private boolean isClass;
    private boolean isEnum;
    private boolean isCollection;


    public InstanceAttribute() {

    }

    public InstanceAttribute(String name, String type, Object values[], boolean isClass, boolean isEnum, boolean isCollection) {
        this.name = name;
        this.type = type;
        this.values = values;
        this.isClass = isClass;
        this.isEnum = isEnum;
        this.isCollection = isCollection;
    }

    public boolean getIsClass() {
        return isClass;
    }

    public boolean getIsEnum() {
        return isEnum;
    }

    public boolean getIsCollection() {
        return isCollection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object values[]) {
        this.values = values;
    }

    public boolean isClass() {
        return isClass;
    }

    public void setClass(boolean aClass) {
        isClass = aClass;
    }

    public boolean isEnum() {
        return isEnum;
    }

    public void setEnum(boolean anEnum) {
        isEnum = anEnum;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }
}
