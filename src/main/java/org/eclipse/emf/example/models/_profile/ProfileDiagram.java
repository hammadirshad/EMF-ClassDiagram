package org.eclipse.emf.example.models._profile;

import org.eclipse.emf.example.models._enum.EnumStructure;

import java.util.ArrayList;

public class ProfileDiagram {

    private String name;
    private ArrayList<EnumStructure> enumerations = new ArrayList<>();
    private ArrayList<StereotypeStructure> stereotypes = new ArrayList<>();
    private ArrayList<ExtensionStructure> extensions = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<EnumStructure> getEnumerations() {
        return enumerations;
    }

    public void setEnumerations(ArrayList<EnumStructure> enumerations) {
        this.enumerations = enumerations;
    }

    public ArrayList<StereotypeStructure> getStereotypes() {
        return stereotypes;
    }

    public void setStereotypes(ArrayList<StereotypeStructure> stereotypes) {
        this.stereotypes = stereotypes;
    }

    public ArrayList<ExtensionStructure> getExtensions() {
        return extensions;
    }

    public void setExtensions(ArrayList<ExtensionStructure> extensions) {
        this.extensions = extensions;
    }
}
