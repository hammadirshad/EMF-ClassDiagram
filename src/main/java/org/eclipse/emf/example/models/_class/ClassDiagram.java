package org.eclipse.emf.example.models._class;


import org.eclipse.emf.example.models._enum.EnumStructure;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassDiagram implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<ClassStructure> classes = new ArrayList<>();
    private ArrayList<ClassInstance> instances = new ArrayList<>();
    private ArrayList<EnumStructure> enumerations = new ArrayList<>();

    public ArrayList<ClassStructure> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<ClassStructure> classes) {
        this.classes = classes;
    }

    public void addClass(ClassStructure _class) {
        this.classes.add(_class);
    }

    public ArrayList<ClassInstance> getInstances() {
        return instances;
    }

    public void setInstances(ArrayList<ClassInstance> instances) {
        this.instances = instances;
    }

    public void addInstance(ClassInstance instance) {
        this.instances.add(instance);
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


}
