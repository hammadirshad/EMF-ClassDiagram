package org.eclipse.emf.example.models._class;

import java.util.ArrayList;
import java.util.List;

public class ClassInstance{

    private String _package;
    private String name;
    private List<ClassStructure> classes=new ArrayList<>();
    private List<InstanceAttribute> attributes = new ArrayList<>();


    public void addAttribute(InstanceAttribute attribute) {
        this.attributes.add(attribute);
    }

    public String get_package() {
        return _package;
    }

    public void set_package(String _package) {
        this._package = _package;
    }

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

    public List<InstanceAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<InstanceAttribute> attributes) {
        this.attributes = attributes;
    }
}
