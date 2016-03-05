package org.emf.example.dataobject;

import java.util.ArrayList;

public class ClassStructure {

	private String _package;
	private ArrayList<String> imports = new ArrayList<String>();
	private String className;
	private String classVisibility;
	private String classType;
	private boolean isAbstract;
	private boolean isFinal;
	private ArrayList<ClassAttribute> classAttributes = new ArrayList<ClassAttribute>();
	private ArrayList<ClassMethod> classMethods = new ArrayList<ClassMethod>();
	private ArrayList<ClassRelation> classRelationships = new ArrayList<ClassRelation>();

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassVisibility() {
		return this.classVisibility;
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

	public ArrayList<ClassRelation> getRelationships() {
		return classRelationships;
	}

	public void setRelationships(ArrayList<ClassRelation> relationships) {
		classRelationships = relationships;
	}

	public void addRelationship(ClassRelation relationship) {
		classRelationships.add(relationship);
	}

	public String getPackage() {
		return _package;
	}

	public void setPackage(String _package) {
		this._package = _package;
	}

	public String get_package() {
		return _package;
	}

	public void set_package(String _package) {
		this._package = _package;
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

	public ArrayList<ClassRelation> getClassRelationships() {
		return classRelationships;
	}

	public void setClassRelationships(
			ArrayList<ClassRelation> classRelationships) {
		this.classRelationships = classRelationships;
	}

	public void addClassRelationship(ClassRelation classRelationship) {
		this.classRelationships.add(classRelationship);
	}

	public void setClassVisibility(String classVisibility) {
		this.classVisibility = classVisibility;
	}

}
