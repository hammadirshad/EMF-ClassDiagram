package org.emf.example.dataobject;

import java.util.ArrayList;

public class ClassDiagram {
	ArrayList<ClassStructure> classes = null;
	ArrayList<ClassRelation> relationships = null;

	public ClassDiagram() {
		classes = new ArrayList<ClassStructure>();
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
