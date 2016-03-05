package org.emf.example.dataobject;

public class ClassAttribute {
	private String attributeName;
	private String attributeType;
	private String attributeVisibility;
	private String direction;
	private Object Value;
	private boolean Class;
	private boolean collection;
	
	

	public ClassAttribute(String attributeName, String attributeType, String attributeVisibility, String direction,
			Object value, boolean class1, boolean collection) {
		super();
		this.attributeName = attributeName;
		this.attributeType = attributeType;
		this.attributeVisibility = attributeVisibility;
		this.direction = direction;
		Value = value;
		Class = class1;
		this.collection = collection;
	}

	public ClassAttribute() {
		attributeName = "";
		attributeType = "";
		attributeVisibility = "";
		 Class=false;
		 collection=false;
	}

	public ClassAttribute(String variableName, String variableType,
			String variableVisibility) {
		this.attributeName = variableName;
		this.attributeType = variableType;
		this.attributeVisibility = variableVisibility;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	public String getAttributeVisibility() {
		return attributeVisibility;
	}

	public void setAttributeVisibility(String attributeVisibility) {
		this.attributeVisibility = attributeVisibility;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Object getValue() {
		return Value;
	}

	public void setValue(Object value) {
		Value = value;
	}

	public boolean isClass() {
		return Class;
	}

	public void setClass(boolean class1) {
		Class = class1;
	}

	public boolean isCollection() {
		return collection;
	}

	public void setCollection(boolean collection) {
		this.collection = collection;
	}

	public String printAttribute() {
		String variable = "";
		variable += this.attributeType + " " + this.attributeName;
		return variable;

	}

	public String printCompleteVariable() {
		String variable = "";
		variable += this.attributeVisibility + " " + this.attributeType + " "
				+ this.attributeName;
		return variable;
	}

}
