package org.emf.example.dataobject;

import java.util.ArrayList;
import java.util.List;

public class ClassInstance implements Cloneable {

    private String _package;
    private String name;
    private List<InstanceAttribute> attributes = new ArrayList<InstanceAttribute>();


    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


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

    public List<InstanceAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<InstanceAttribute> attributes) {
        this.attributes = attributes;
    }
}
