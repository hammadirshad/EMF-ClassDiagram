package org.emf.example.reader;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.impl.ClassImpl;
import org.eclipse.uml2.uml.internal.impl.EnumerationImpl;
import org.eclipse.uml2.uml.internal.impl.InterfaceImpl;
import org.eclipse.uml2.uml.internal.impl.PrimitiveTypeImpl;
import org.emf.example.dataobject.ClassAttribute;
import org.emf.example.dataobject.ClassDiagram;
import org.emf.example.dataobject.ClassMethod;
import org.emf.example.dataobject.ClassRelation;
import org.emf.example.dataobject.ClassStructure;
import org.emf.example.model.ModelLoader;

public class ClassDiagramReader implements Serializable {
	private static final long serialVersionUID = 1L;
	private static ClassDiagram refModelDetail;
	private ModelLoader umlModel = null;

	public ClassDiagramReader() {
		refModelDetail = new ClassDiagram();
		umlModel = new ModelLoader();
	}

	public ClassDiagram getRefModelDetails(File model) throws Exception {

		Package _package = umlModel.loadModel(model);
		if (_package != null) {
			EList<PackageableElement> packageableElements = _package.getPackagedElements();
			readPackage(packageableElements);
		} else {
			System.err.println("Package is null");
		}

		return refModelDetail;
	}

	private void readPackage(EList<PackageableElement> packageableElements) {

		for (PackageableElement element : packageableElements) {

			if (element.eClass() == UMLPackage.Literals.ASSOCIATION) {
				refModelDetail.addRelationship(readAssociation(element));
			} else if (element.eClass() == UMLPackage.Literals.CLASS) {
				refModelDetail.addClass(readClass(element, refModelDetail.getRelationships()));
			} else if (element.eClass() == UMLPackage.Literals.PACKAGE) {
				Package _package = (Package) element;
				readPackage(_package.getPackagedElements());

			}
		}
	}

	private ClassStructure readClass(Element element, ArrayList<ClassRelation> allRelationships) {

		ClassStructure structure = new ClassStructure();

		Class _class = (Class) element;
		structure.setAbstract(_class.isAbstract());
		structure.setFinal(_class.isLeaf());
		structure.setClassName(_class.getName());
		structure.setVisibility(_class.getVisibility().toString());

		structure.setClassAttributes(readAttributes(_class));
		structure.setClassMethods(readClassOperations(_class));
		structure.setClassRelationships(readClassRelations(_class, allRelationships));

		return structure;
	}

	private ArrayList<ClassRelation> readClassRelations(Class _class, ArrayList<ClassRelation> allRelationships) {
		ArrayList<ClassRelation> list = new ArrayList<ClassRelation>();
		EList<Relationship> classRelationships = _class.getRelationships();
		for (Relationship classRelation : classRelationships) {
			for (Element elements : classRelation.getRelatedElements()) {
				ClassImpl relationName = (ClassImpl) elements;
				if (!relationName.getName().equals(_class.getName())) {

					for (ClassRelation relation : allRelationships) {

						if (relation.getClass_1() != null && relation.getClass_2() != null)
							if (relation.getClass_1().equals(_class.getName().toString())
									&& relation.getClass_2().equals(relationName.getName().toString())) {
								list.add(relation);
							} else if (relation.getClass_1().equals(relationName.getName().toString())
									&& relation.getClass_2().equals(_class.getName().toString())) {
								list.add(relation);

							}
					}

				}
			}
		}
		return list;
	}

	private ArrayList<ClassMethod> readClassOperations(Class _class) {
		ArrayList<ClassMethod> list = new ArrayList<ClassMethod>();
		List<org.eclipse.uml2.uml.Operation> operations = _class.getOwnedOperations();
		if (!operations.isEmpty()) {
			for (org.eclipse.uml2.uml.Operation oper : operations) {

				ClassMethod operation = new ClassMethod();
				operation.setMethodName(oper.getName());
				operation.setMethodVisibility(oper.getVisibility().toString());

				EList<Parameter> parameters = oper.getOwnedParameters();
				if (!parameters.isEmpty()) {
					for (Parameter parameter : parameters) {

						boolean returnType = false;
						ParameterDirectionKind direction = (ParameterDirectionKind) parameter.getDirection();
						if (direction.getValue() == 3) {
							returnType = true;
						}

						ClassAttribute attr = new ClassAttribute();

						if (parameter.getType() instanceof org.eclipse.uml2.uml.internal.impl.PrimitiveTypeImpl) {
							PrimitiveTypeImpl prim = (PrimitiveTypeImpl) (parameter.getType());
							if (returnType) {
								if (prim.getName() == null || prim.getName().equals("")) {
									operation.setMethodReturnType(prim.eProxyURI().fragment());
								} else {
									operation.setMethodReturnType(prim.getName());
								}

							} else {
								attr.setAttributeName(parameter.getName());

								if (prim.getName() == null || prim.getName().equals("")) {
									attr.setAttributeType(prim.eProxyURI().fragment());
								} else {
									attr.setAttributeType(prim.getName());
								}
								operation.getMethodParameters().add(attr);
							}
						} else if (parameter.getType() instanceof org.eclipse.uml2.uml.internal.impl.EnumerationImpl) {

							EnumerationImpl impl = (EnumerationImpl) (parameter.getType());

							if (returnType) {
								operation.setMethodReturnType(impl.getName());
							} else {

								attr.setAttributeName(parameter.getName());
								attr.setAttributeVisibility(parameter.getVisibility().toString());
								attr.setAttributeType(impl.getName());
								operation.getMethodParameters().add(attr);
							}

						} else if (parameter.getType() instanceof org.eclipse.uml2.uml.internal.impl.ClassImpl) {
							ClassImpl impl = (ClassImpl) (parameter.getType());
							if (returnType) {
								operation.setMethodReturnType(impl.getName());
							} else {

								attr.setAttributeName(parameter.getName());
								attr.setAttributeVisibility(parameter.getVisibility().toString());
								attr.setAttributeType(impl.getName());
								attr.setClass(true);

								if (parameter.getUpper() == -1) {
									attr.setCollection(true);
								}
								operation.getMethodParameters().add(attr);
							}
						} else if (parameter.getType() instanceof org.eclipse.uml2.uml.internal.impl.InterfaceImpl) {

							InterfaceImpl prim = (InterfaceImpl) (parameter.getType());
							URI proxy = prim.eProxyURI();
							String proxyFragment = proxy.fragment();
							String arrtibuteType = "";
							if (proxyFragment != null) {
								if (!proxyFragment.isEmpty()) {
									String collectionType = "";
									if (proxyFragment.contains("java.util.List")) {
										attr.setCollection(true);
										collectionType = "java.util.List";
									} else if (proxyFragment.contains("java.util.ArrayList")) {
										attr.setCollection(true);
										collectionType = "java.util.ArrayList";
									}

									if (attr.isCollection()) {
										int startIndex = proxyFragment.indexOf(collectionType + "[project^id=");
										startIndex += (collectionType + "[project^id=").length();
										String temp = arrtibuteType = proxyFragment.substring(startIndex,
												proxyFragment.indexOf("]$uml.Interface"));
										arrtibuteType = collectionType + "<" + temp + ">";

									} else {

										int startIndex = proxyFragment.indexOf(collectionType + "[project^id=");
										startIndex += ("[project^id=").length();

										arrtibuteType = proxyFragment.substring(startIndex,
												proxyFragment.indexOf("]$uml.Interface"));
									}
								}
							}

							if (returnType) {
								operation.setMethodReturnType(arrtibuteType);
							} else {
								attr.setAttributeType(arrtibuteType);
								attr.setAttributeName(parameter.getName().toString());
								attr.setAttributeVisibility(parameter.getVisibility().toString());
								operation.getMethodParameters().add(attr);
							}
						}

					}
				}

				list.add(operation);
			}
		}
		return list;
	}

	private ArrayList<ClassAttribute> readAttributes(Class _class) {
		ArrayList<ClassAttribute> list = new ArrayList<ClassAttribute>();
		EList<Property> attributes = _class.getOwnedAttributes();
		if (!attributes.isEmpty()) {
			for (Property attribute : attributes) {
				ClassAttribute attr = new ClassAttribute();

				if (attribute.getType() instanceof org.eclipse.uml2.uml.internal.impl.PrimitiveTypeImpl) {

					attr.setAttributeName(attribute.getName().toString());
					attr.setAttributeVisibility(attribute.getVisibility().toString());
					PrimitiveTypeImpl prim = (PrimitiveTypeImpl) (attribute.getType());
					if (prim.getName() == null || prim.getName().equals("")) {
						attr.setAttributeType(prim.eProxyURI().fragment());
					} else {
						attr.setAttributeType(prim.getName());
					}

					list.add(attr);
				} else if (attribute.getType() instanceof org.eclipse.uml2.uml.internal.impl.EnumerationImpl) {

					EnumerationImpl impl = (EnumerationImpl) (attribute.getType());
					attr.setAttributeName(attribute.getName().toString());
					attr.setAttributeVisibility(attribute.getVisibility().toString());
					attr.setAttributeType(impl.getName());
					list.add(attr);
				} else if (attribute.getType() instanceof org.eclipse.uml2.uml.internal.impl.ClassImpl) {

					ClassImpl impl = (ClassImpl) (attribute.getType());
					attr.setAttributeName(attribute.getName().toString());
					attr.setAttributeVisibility(attribute.getVisibility().toString());
					attr.setAttributeType(impl.getName());
					attr.setClass(true);

					if (attribute.getUpper() == -1) {
						attr.setCollection(true);
					}
					list.add(attr);
				} else if (attribute.getType() instanceof org.eclipse.uml2.uml.internal.impl.InterfaceImpl) {

					InterfaceImpl prim = (InterfaceImpl) (attribute.getType());
					URI proxy = prim.eProxyURI();
					String proxyFragment = proxy.fragment();
					String arrtibuteType = "";
					if (proxyFragment != null) {
						if (!proxyFragment.isEmpty()) {
							String collectionType = "";
							if (proxyFragment.contains("java.util.List")) {
								attr.setCollection(true);
								collectionType = "java.util.List";
							} else if (proxyFragment.contains("java.util.ArrayList")) {
								attr.setCollection(true);
								collectionType = "java.util.ArrayList";
							}

							if (attr.isCollection()) {
								int startIndex = proxyFragment.indexOf(collectionType + "[project^id=");
								startIndex += (collectionType + "[project^id=").length();
								String temp = arrtibuteType = proxyFragment.substring(startIndex,
										proxyFragment.indexOf("]$uml.Interface"));
								arrtibuteType = collectionType + "<" + temp + ">";

							} else {

								int startIndex = proxyFragment.indexOf(collectionType + "[project^id=");
								startIndex += ("[project^id=").length();

								arrtibuteType = proxyFragment.substring(startIndex,
										proxyFragment.indexOf("]$uml.Interface"));
							}
						}
					}
					attr.setAttributeType(arrtibuteType);
					attr.setAttributeName(attribute.getName().toString());
					attr.setAttributeVisibility(attribute.getVisibility().toString());
					list.add(attr);
				}

			}
		}

		return list;
	}

	private ClassRelation readAssociation(Element element) {
		Association association = (Association) element;
		ClassRelation structure = new ClassRelation();
		boolean first = true;
		for (Property end : association.getMemberEnds()) {
			if (end.getType() instanceof org.eclipse.uml2.uml.Class) {
				if (first) {
					first = false;
					structure.setVisibility(end.getVisibility().toString());
					structure.setClass_1(end.getType().getName());
					structure.setRole_Name_1(end.getName().toString());
					structure.setNavigable_1(end.isNavigable());

					if (end instanceof org.eclipse.uml2.uml.LiteralUnlimitedNatural) {

						org.eclipse.uml2.uml.LiteralUnlimitedNatural u = (org.eclipse.uml2.uml.LiteralUnlimitedNatural) end
								.getUpperValue();
						structure.setMultipcity_Uper_1(u.getValue());

						org.eclipse.uml2.uml.LiteralUnlimitedNatural l = (org.eclipse.uml2.uml.LiteralUnlimitedNatural) end
								.getLowerValue();
						structure.setMultipcity_Lower_1(l.getValue());

					} else {

						structure.setMultipcity_Uper_1(0);
						structure.setMultipcity_Lower_1(0);

					}

				} else {

					structure.setClass_2(end.getType().getName());
					structure.setRole_Name_2(end.getName().toString());
					structure.setNavigable_2(end.isNavigable());

					if (end instanceof org.eclipse.uml2.uml.LiteralUnlimitedNatural) {
						org.eclipse.uml2.uml.LiteralUnlimitedNatural l = (org.eclipse.uml2.uml.LiteralUnlimitedNatural) end
								.getLowerValue();
						structure.setMultipcity_Lower_2(l.getValue());
					} else {
						structure.setMultipcity_Lower_2(0);
					}

					org.eclipse.uml2.uml.LiteralUnlimitedNatural upper = (org.eclipse.uml2.uml.LiteralUnlimitedNatural) end
							.getUpperValue();
					if (upper != null) {
						structure.setMultipcity_Uper_2(upper.getValue());
					}

					return structure;
				}

			}

		}
		return structure;
	}

}
