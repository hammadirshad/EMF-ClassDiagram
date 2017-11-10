package org.eclipse.emf.example.models._package;

import org.eclipse.emf.example.models._class.ClassInstance;
import org.eclipse.emf.example.models._class.ClassStructure;
import org.eclipse.emf.example.models._enum.EnumStructure;

import java.util.ArrayList;
import java.util.List;

public class PackageStructure {

    private String name;
    private List<ClassStructure> classes = new ArrayList<>();
    private List<ClassInstance> instances = new ArrayList<>();
    private List<EnumStructure> enums = new ArrayList<>();
    private List<PackageStructure> packages = new ArrayList<>();



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClassStructure> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassStructure> classes) {
        this.classes = classes;
    }

    public List<ClassInstance> getInstances() {
        return instances;
    }

    public void setInstances(List<ClassInstance> instances) {
        this.instances = instances;
    }

    public List<EnumStructure> getEnums() {
        return enums;
    }

    public void setEnums(List<EnumStructure> enums) {
        this.enums = enums;
    }

    public List<PackageStructure> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageStructure> packages) {
        this.packages = packages;
    }
}
