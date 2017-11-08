package org.eclipse.emf.example.models._class;


import org.eclipse.emf.example.models._enum.EnumStructure;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassDiagram implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<ClassStructure> classes = null;
    private ArrayList<EnumStructure> enumerations = null;
    private ArrayList<ClassRelation> relationships = null;

    public ClassDiagram() {
        classes = new ArrayList<ClassStructure>();
        enumerations = new ArrayList<EnumStructure>();
        relationships = new ArrayList<ClassRelation>();
    }

    public ArrayList<ClassStructure> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<ClassStructure> classes) {
        this.classes = classes;
    }

    public void addClass(ClassStructure _class) {
        this.classes.add(_class);
    }

    public ArrayList<EnumStructure> getEnumerations() {
        return enumerations;
    }

    public void setEnumerations(ArrayList<EnumStructure> enumerations) {
        this.enumerations = enumerations;
    }

    public void addEnumeration(EnumStructure enumeration) {
        this.enumerations.add(enumeration);
    }

    public ArrayList<ClassRelation> getRelationships() {
        return relationships;
    }

    public void setRelationships(ArrayList<ClassRelation> relationships) {
        this.relationships = relationships;
    }

    public void addRelationship(ClassRelation relationship) {
        this.relationships.add(relationship);
    }

}
