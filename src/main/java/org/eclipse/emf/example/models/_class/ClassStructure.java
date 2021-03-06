package org.eclipse.emf.example.models._class;

import java.util.ArrayList;
import java.util.List;

public class ClassStructure {

    private String _package;
    private List<String> imports = new ArrayList<>();
    private String name;
    private String visibility;
    private String type;
    private boolean _abstract;
    private boolean _final;
    private List<String> rules = new ArrayList<>();
    private List<ClassInstance> instances = new ArrayList<>();
    private List<ClassStructure> superClasses = new ArrayList<>();
    private List<ClassAttribute> attributes = new ArrayList<>();
    private List<ClassOperation> operations = new ArrayList<>();
    private List<ClassRelation> relationships = new ArrayList<>();


    public void addRelationship(ClassRelation relationship) {
        relationships.add(relationship);
    }

    public void addImport(String _import) {
        this.imports.add(_import);
    }

    public void addRules(String rule) {
        this.rules.add(rule);
    }

    public void addOperation(ClassOperation operation) {
        this.operations.add(operation);
    }

    public void addAttribute(ClassAttribute attribute) {
        this.attributes.add(attribute);
    }

    public void addInstance(ClassInstance instance) {
        this.instances.add(instance);
    }

    public void addSuperClass(ClassStructure superClass) {
        this.superClasses.add(superClass);
    }

    public String getPackage() {
        return _package;
    }

    public void setPackage(String _package) {
        this._package = _package;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public boolean isAbstract() {
        return _abstract;
    }

    public void setAbstract(boolean _abstract) {
        this._abstract = _abstract;
    }

    public boolean isFinal() {
        return _final;
    }

    public void setFinal(boolean _final) {
        this._final = _final;
    }

    public List<ClassStructure> getSuperClasses() {
        return superClasses;
    }

    public void setSuperClasses(List<ClassStructure> superClasses) {
        this.superClasses = superClasses;
    }

    public List<ClassAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ClassAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<ClassOperation> getOperations() {
        return operations;
    }

    public void setOperations(List<ClassOperation> operations) {
        this.operations = operations;
    }

    public List<ClassRelation> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<ClassRelation> relationships) {
        this.relationships = relationships;
    }

    public List<ClassInstance> getInstances() {
        return instances;
    }

    public void setInstances(List<ClassInstance> instances) {
        this.instances = instances;
    }


}
