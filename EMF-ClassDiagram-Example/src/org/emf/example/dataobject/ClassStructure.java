package org.emf.example.dataobject;

import java.util.ArrayList;
import java.util.List;

public class ClassStructure {

    private String classPackage;
    private ArrayList<String> imports = new ArrayList<String>();
    private String className;
    private String classVisibility;
    private String classType;
    private List<String> rules = new ArrayList<String>();
    private boolean isAbstract;
    private boolean isFinal;
    private ArrayList<ClassAttribute> classAttributes = new ArrayList<ClassAttribute>();
    private ArrayList<ClassMethod> classMethods = new ArrayList<ClassMethod>();
    private ArrayList<ClassRelations> classRelationships = new ArrayList<ClassRelations>();

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassVisibility() {
        return this.classVisibility;
    }

    public void setClassVisibility(String classVisibility) {
        this.classVisibility = classVisibility;
    }

    public void setClassAccessibility(String classVisibility) {
        this.classVisibility = classVisibility;
    }

    public ArrayList<ClassAttribute> getClassAttributes() {
        return this.classAttributes;
    }

    public void setClassAttributes(ArrayList<ClassAttribute> classAttributes) {
        this.classAttributes = classAttributes;
    }

    public ArrayList<ClassMethod> getClassMethods() {
        return classMethods;
    }

    public void setClassMethods(ArrayList<ClassMethod> classMethods) {
        this.classMethods = classMethods;
    }

    public void addClassMethods(ClassMethod classMethod) {
        this.classMethods.add(classMethod);
    }

    public String getType() {
        return classType;
    }

    public void setType(String type) {
        classType = type;
    }

    public String getVisibility() {
        return classVisibility;
    }

    public void setVisibility(String visibility) {
        classVisibility = visibility;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean abstract1) {
        isAbstract = abstract1;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean final1) {
        isFinal = final1;
    }

    public ArrayList<ClassRelations> getRelationships() {
        return classRelationships;
    }

    public void setRelationships(ArrayList<ClassRelations> relationships) {
        classRelationships = relationships;
    }

    public void addRelationship(ClassRelations relationship) {
        classRelationships.add(relationship);
    }

    public String getClassPackage() {
        return classPackage;
    }

    public void setClassPackage(String classPackage) {
        this.classPackage = classPackage;
    }

    public ArrayList<String> getImports() {
        return imports;
    }

    public void setImports(ArrayList<String> imports) {
        this.imports = imports;
    }

    public void addImport(String _import) {
        this.imports.add(_import);
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public ArrayList<ClassRelations> getClassRelationships() {
        return classRelationships;
    }

    public void setClassRelationships(
            ArrayList<ClassRelations> classRelationships) {
        this.classRelationships = classRelationships;
    }

    public void addClassRelationship(ClassRelations classRelationship) {
        this.classRelationships.add(classRelationship);
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public void addRules(String rule) {
        this.rules.add(rule);
    }
}
