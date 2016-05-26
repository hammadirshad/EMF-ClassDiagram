package org.emf.example.reader;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.uml2.uml.*;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.internal.impl.*;
import org.emf.example.dataobject.*;
import org.emf.example.model.ModelLoader;
import org.emf.example.util.Keywords;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassDiagramReader implements Serializable {
    private static final long serialVersionUID = 1L;
    private ModelLoader loader;
    private static ClassDiagram refModelDetail;
    private Map<String, String> superClassMap;
    private Map<String, ClassStructure> classMap;
    private Map<String, List<ClassInstance>> classInstanceMap;


    public ClassDiagramReader() {
        loader = new ModelLoader();
        refModelDetail = new ClassDiagram();
        superClassMap = new HashMap<String, String>();
        classMap = new HashMap<String, ClassStructure>();
        classInstanceMap = new HashMap<>();
    }


    public ClassDiagram getRefModelDetails(File file) throws Exception {
        Package _package = loader.loadModel(file);
        if (file != null) {
            EList<PackageableElement> packageableElements = _package.getPackagedElements();
            String packageName = file.getName() != null ? file.getName() : "";
            readPackage(packageableElements, packageName);
        } else {
            System.err.println("Package is null");
        }

        return refModelDetail;
    }


    private void readPackage(EList<PackageableElement> packageableElements, String packageName) {

        for (PackageableElement element : packageableElements) {

            if (element.eClass() == UMLPackage.Literals.CLASS) {
                ClassStructure cs = readClass(element, packageName);
                classMap.put(cs.getName(), cs);
            } else if (element.eClass() == UMLPackage.Literals.ENUMERATION) {
                refModelDetail.addEnumeration(readEnumeration(element, packageName));
            } else if (element.eClass() == UMLPackage.eINSTANCE.getInstanceSpecification()) {
                readInstance(element, packageName);
            } else if (element.eClass() == UMLPackage.Literals.PACKAGE) {
                Package _package = (Package) element;
                String newPackageName;

                if (packageName.equals("")) {
                    newPackageName = _package.getName() != null
                            ? _package.getName()
                            : packageName;
                } else {
                    newPackageName = _package.getName() != null
                            ? packageName + "." + _package.getName()
                            : packageName;
                }
                readPackage(_package.getPackagedElements(), newPackageName);

            }
        }
    }

    private void readInstance(PackageableElement element, String packageName) {
        InstanceSpecification instance = (InstanceSpecification) element;
        if (instance.getName() != null && !instance.getName().isEmpty()) {
            ClassInstance classInstance = new ClassInstance();
            classInstance.setName(instance.getName());
            classInstance.set_package(packageName);

            for (Slot slot : instance.getSlots()) {

                StructuralFeature feature = slot.getDefiningFeature();

                InstanceAttribute attribute = new InstanceAttribute();
                attribute.setName(feature.getName());
                attribute.setType(feature.getType().getName());

                List<Object> values = new ArrayList<>();
                for (ValueSpecification valueSpecification : slot.getValues()) {

                    if (valueSpecification instanceof InstanceValue) {

                        attribute.setClass(true);
                        InstanceValue instanceValue = (InstanceValue) valueSpecification;

                        InstanceSpecification valueInstanceSpecification = instanceValue.getInstance();

                        if (valueInstanceSpecification != null) {
                            values.add(valueInstanceSpecification.getName());
                        }


                    } else if (valueSpecification instanceof LiteralSpecification) {

                        LiteralSpecification literalSpecification = (LiteralSpecification) valueSpecification;


                        if (literalSpecification instanceof LiteralString) {
                            LiteralString literal = (LiteralString) literalSpecification;
                            values.add(literal.getValue());

                        } else if (literalSpecification instanceof LiteralInteger) {
                            LiteralInteger literal = (LiteralInteger) literalSpecification;
                            values.add(literal.getValue());


                        } else if (literalSpecification instanceof LiteralBoolean) {
                            LiteralBoolean literal = (LiteralBoolean) literalSpecification;
                            values.add(literal.isValue());

                        } else if (literalSpecification instanceof LiteralReal) {
                            LiteralReal literal = (LiteralReal) literalSpecification;
                            values.add(literal.getValue());

                        } else if (literalSpecification instanceof LiteralUnlimitedNatural) {
                            LiteralUnlimitedNatural literal = (LiteralUnlimitedNatural) literalSpecification;
                            values.add(literal.getValue());

                        }

                    }


                }

                attribute.setValues(values.toArray());
                classInstance.addAttribute(attribute);


            }


            for (Classifier classifier : instance.getClassifiers()) {
                if (instance.getName() != null && classifier.getName() != null) {
                    if (classInstanceMap.containsKey(classifier.getName())) {
                        List<ClassInstance> intances = classInstanceMap.get(classifier.getName());
                        intances.add(classInstance);
                        classInstanceMap.put(classifier.getName(), intances);
                    } else {
                        List<ClassInstance> intances = new ArrayList<>();
                        intances.add(classInstance);
                        classInstanceMap.put(classifier.getName(), intances);
                    }
                }
            }
        }


    }

    private EnumStructure readEnumeration(PackageableElement element, String packageName) {

        EnumStructure structure = new EnumStructure();
        Enumeration enumeration = (Enumeration) element;
        structure.setName(enumeration.getName());
        structure.setPackage(packageName);
        for (EnumerationLiteral literal : enumeration.getOwnedLiterals()) {
            structure.addLiteral(literal.getName());
        }

        return structure;
    }

    private ClassStructure readClass(Element element, String packageName) {
        ClassStructure structure = new ClassStructure();
        Class _class = (Class) element;
        List<String> rules = new ArrayList<>();
        System.out.println(_class.getName());


        for (Constraint constraint : _class.getOwnedRules()) {
            if (constraint.getSpecification() instanceof OpaqueExpressionImpl) {
                OpaqueExpressionImpl expressionImpl = (OpaqueExpressionImpl) constraint.getSpecification();
                for (String body : expressionImpl.getBodies()) {
                    rules.add(body);
                }
            }

        }



        /*for (NamedElement namedElement : _class.getInheritedMembers()) {

            System.out.println(namedElement.getClass());
            System.out.println(namedElement.getName());

        }*/

        /*for (Generalization generalization : _class.getGeneralizations()) {
            System.out.println(generalization);
            System.out.println(generalization.getGeneral().getName());
        }*/


//        for (Association association : _class.getAssociations()) {
//            System.out.println(association);
//            System.out.println(association.getName());
//        }


        for (Class superClass : _class.getSuperClasses()) {
            superClassMap.put(_class.getName(), superClass.getName());


            //  System.out.println(superClass.getName());

            break;
        }


        System.out.println("\n  -------- \n");

        structure.setPackage(packageName);
        structure.setVisibility(_class.getVisibility().toString());
        structure.setRules(rules);
        structure.setAbstract(_class.isAbstract());
        structure.setFinal(_class.isLeaf());
        structure.setName(_class.getName());
        structure.setAttributes(readAttribute(_class));
        structure.setOperations(readClassOperations(_class));
        structure.setRelationships(readClassRelations(_class));

        return structure;
    }

    private ArrayList<ClassRelation> readClassRelations(Class _class) {
        ArrayList<ClassRelation> list = new ArrayList<ClassRelation>();
        EList<Relationship> classRelationships = _class.getRelationships();
        for (Relationship relationship : classRelationships) {
            if (relationship.eClass() == UMLPackage.Literals.ASSOCIATION) {
                list.add(readAssociation(relationship));
            } else if (relationship.eClass() == UMLPackage.Literals.GENERALIZATION) {
                list.add(readGeneralization(relationship));
            }

        }
        return list;
    }

    private ArrayList<ClassOperation> readClassOperations(Class _class) {
        ArrayList<ClassOperation> list = new ArrayList<ClassOperation>();
        List<org.eclipse.uml2.uml.Operation> operations = _class.getOwnedOperations();
        if (!operations.isEmpty()) {
            for (org.eclipse.uml2.uml.Operation oper : operations) {

                ClassOperation operation = new ClassOperation();
                operation.setName(oper.getName());
                operation.setVisibility(oper.getVisibility().toString());
                operation.setReturnType(new OperationReturn());

                EList<Parameter> parameters = oper.getOwnedParameters();
                if (!parameters.isEmpty()) {
                    for (Parameter parameter : parameters) {

                        boolean returnType = false;
                        ParameterDirectionKind direction = (ParameterDirectionKind) parameter.getDirection();
                        if (direction.getValue() == 3) {
                            returnType = true;
                        }

                        OperationParameter attr = new OperationParameter();

                        if (parameter
                                .getType() instanceof org.eclipse.uml2.uml.internal.impl.PrimitiveTypeImpl) {
                            PrimitiveTypeImpl prim = (PrimitiveTypeImpl) (parameter.getType());
                            if (returnType) {
                                if (prim.getName() == null || prim.getName().equals("")) {
                                    OperationReturn methodReturn = new OperationReturn();
                                    methodReturn.setType(prim.eProxyURI().fragment());
                                    if (parameter.getUpper() == -1) {
                                        methodReturn.setCollection(true);
                                    }
                                    operation.setReturnType(methodReturn);
                                } else {
                                    OperationReturn methodReturn = new OperationReturn();
                                    methodReturn.setType(prim.getName());
                                    if (parameter.getUpper() == -1) {
                                        methodReturn.setCollection(true);
                                    }
                                    operation.setReturnType(methodReturn);

                                }

                            } else {
                                attr.setName(parameter.getName());

                                if (prim.getName() == null || prim.getName().equals("")) {
                                    attr.setType(prim.eProxyURI().fragment());
                                    if (parameter.getUpper() == -1) {
                                        attr.setCollection(true);
                                    }
                                } else {
                                    if (parameter.getUpper() == -1) {
                                        attr.setCollection(true);
                                    }
                                    attr.setType(prim.getName());
                                }
                                operation.getParameters().add(attr);
                            }
                        } else if (parameter
                                .getType() instanceof org.eclipse.uml2.uml.internal.impl.EnumerationImpl) {

                            EnumerationImpl impl = (EnumerationImpl) (parameter.getType());

                            if (returnType) {
                                OperationReturn methodReturn = new OperationReturn();
                                methodReturn.setType(impl.getName());
                                if (parameter.getUpper() == -1) {
                                    methodReturn.setCollection(true);
                                }
                                operation.setReturnType(methodReturn);

                            } else {

                                attr.setName(parameter.getName());
                                attr.setVisibility(parameter.getVisibility().toString());
                                attr.setType(impl.getName());
                                if (parameter.getUpper() == -1) {
                                    attr.setCollection(true);
                                }
                                operation.getParameters().add(attr);
                            }

                        } else if (parameter
                                .getType() instanceof org.eclipse.uml2.uml.internal.impl.ClassImpl) {
                            ClassImpl impl = (ClassImpl) (parameter.getType());
                            if (returnType) {
                                OperationReturn methodReturn = new OperationReturn();
                                methodReturn.setType(impl.getName());
                                methodReturn.setClass(true);
                                if (parameter.getUpper() == -1) {
                                    methodReturn.setCollection(true);
                                }
                                operation.setReturnType(methodReturn);

                            } else {

                                attr.setName(parameter.getName());
                                attr.setVisibility(parameter.getVisibility().toString());
                                attr.setType(impl.getName());
                                attr.setClass(true);

                                if (parameter.getUpper() == -1) {
                                    attr.setCollection(true);
                                }
                                operation.getParameters().add(attr);
                            }
                        } else if (parameter
                                .getType() instanceof org.eclipse.uml2.uml.internal.impl.InterfaceImpl) {

                            InterfaceImpl prim = (InterfaceImpl) (parameter.getType());
                            URI proxy = prim.eProxyURI();
                            String proxyFragment = proxy.fragment();
                            String arrtibuteType = attributeInterface(proxyFragment);
                            attr.setType(arrtibuteType);

                            if (returnType) {
                                OperationReturn methodReturn = new OperationReturn();
                                methodReturn.setType(arrtibuteType);
                                if (attr.isCollection()) {
                                    methodReturn.setCollection(true);
                                }
                                operation.setReturnType(methodReturn);

                            } else {
                                attr.setType(arrtibuteType);
                                attr.setName(parameter.getName().toString());
                                attr.setVisibility(parameter.getVisibility().toString());
                                operation.getParameters().add(attr);
                            }
                        }

                    }
                }

                list.add(operation);
            }
        }
        return list;
    }

    private ArrayList<ClassAttribute> readAttribute(Class _class) {
        ArrayList<ClassAttribute> list = new ArrayList<ClassAttribute>();
        EList<Property> attributes = _class.getOwnedAttributes();
        if (!attributes.isEmpty()) {
            for (Property attribute : attributes) {
                ClassAttribute attr = new ClassAttribute();

                if (attribute.getType() instanceof org.eclipse.uml2.uml.internal.impl.PrimitiveTypeImpl) {

                    attr.setName(attribute.getName().toString());
                    attr.setVisibility(attribute.getVisibility().toString());
                    PrimitiveTypeImpl prim = (PrimitiveTypeImpl) (attribute.getType());
                    if (prim.getName() == null || prim.getName().equals("")) {
                        attr.setType(prim.eProxyURI().fragment());
                    } else {
                        attr.setType(prim.getName());
                    }

                    list.add(attr);
                } else if (attribute
                        .getType() instanceof org.eclipse.uml2.uml.internal.impl.EnumerationImpl) {

                    EnumerationImpl impl = (EnumerationImpl) (attribute.getType());
                    attr.setName(attribute.getName().toString());
                    attr.setVisibility(attribute.getVisibility().toString());
                    attr.setType(impl.getName());
                    attr.setEnum(true);
                    list.add(attr);

                } else if (attribute.getType() instanceof org.eclipse.uml2.uml.internal.impl.ClassImpl) {

                    ClassImpl impl = (ClassImpl) (attribute.getType());
                    attr.setName(attribute.getName().toString());
                    attr.setVisibility(attribute.getVisibility().toString());
                    attr.setType(impl.getName());
                    attr.setClass(true);

                    if (attribute.getUpper() == -1) {
                        attr.setCollection(true);
                    }
                    list.add(attr);
                } else if (attribute
                        .getType() instanceof org.eclipse.uml2.uml.internal.impl.InterfaceImpl) {

                    InterfaceImpl prim = (InterfaceImpl) (attribute.getType());
                    URI proxy = prim.eProxyURI();
                    String proxyFragment = proxy.fragment();
                    attr.setCollection(attributeInterfaceCollection(proxyFragment));
                    String arrtibuteType = attributeInterface(proxyFragment);
                    attr.setType(arrtibuteType);
                    attr.setName(attribute.getName().toString());
                    attr.setVisibility(attribute.getVisibility().toString());
                    list.add(attr);
                }

            }
        }

        return list;
    }


    private ClassRelation readGeneralization(Element element) {
        Generalization generalization = (Generalization) element;
        ClassRelation relation = new ClassRelation();
        relation.setType(Keywords.Generalization);
        boolean first = true;
        for (Element elements : generalization.getRelatedElements()) {
            if (elements instanceof Class) {
                ClassImpl relationClass = (ClassImpl) elements;
                if (first) {
                    first = false;
                    relation.setClass_1(relationClass.getName());
                } else {
                    relation.setClass_2(relationClass.getName());
                }

            }
        }
        refModelDetail.addRelationship(relation);
        return relation;
    }


    private ClassRelation readAssociation(Element element) {
        Association association = (Association) element;

        ClassRelation relation = new ClassRelation();
        relation.setType(Keywords.Association);
        boolean first = true;
        for (Property end : association.getMemberEnds()) {
            if (end.getType() instanceof org.eclipse.uml2.uml.Class) {
                if (first) {
                    first = false;
                    relation.setVisibility(end.getVisibility().toString());

                    relation.setClass_1(end.getType().getName());
                    if (end.getName() != null && !end.getName().isEmpty()) {
                        relation.setRole_Name_1(end.getName());
                    } else {
                        relation.setRole_Name_1("");
                    }
                    relation.setNavigable_1(end.isNavigable());


                    org.eclipse.uml2.uml.LiteralUnlimitedNatural upperValue =
                            (org.eclipse.uml2.uml.LiteralUnlimitedNatural) end.getUpperValue();
                    if (upperValue != null) {
                        relation.setMultipcity_Uper_1(upperValue.getValue());
                    }

                    if (end instanceof org.eclipse.uml2.uml.LiteralUnlimitedNatural) {

                        org.eclipse.uml2.uml.LiteralUnlimitedNatural lowerValue =
                                (org.eclipse.uml2.uml.LiteralUnlimitedNatural) end.getLowerValue();
                        relation.setMultipcity_Lower_1(lowerValue.getValue());

                    }


                } else {

                    relation.setClass_2(end.getType().getName());
                    if (end.getName() != null && !end.getName().isEmpty()) {
                        relation.setRole_Name_2(end.getName());
                    } else {
                        relation.setRole_Name_2("");
                    }
                    relation.setNavigable_2(end.isNavigable());

                    org.eclipse.uml2.uml.LiteralUnlimitedNatural upperValue =
                            (org.eclipse.uml2.uml.LiteralUnlimitedNatural) end.getUpperValue();
                    if (upperValue != null) {
                        relation.setMultipcity_Uper_2(upperValue.getValue());
                    }

                    if (end instanceof org.eclipse.uml2.uml.LiteralUnlimitedNatural) {

                        org.eclipse.uml2.uml.LiteralUnlimitedNatural lowerValue =
                                (org.eclipse.uml2.uml.LiteralUnlimitedNatural) end.getLowerValue();
                        relation.setMultipcity_Lower_2(lowerValue.getValue());
                    }

                    return relation;
                }

            }

        }

        refModelDetail.addRelationship(relation);
        return relation;
    }


    private boolean attributeInterfaceCollection(String proxyFragment) {
        boolean isCollection = false;
        if (proxyFragment != null && !proxyFragment.isEmpty()) {
            if (proxyFragment.contains("java.util.List") || proxyFragment.contains("java.util.ArrayList")) {
                isCollection = true;
            }
        }
        return isCollection = true;
    }

    private String attributeInterface(String proxyFragment) {
        String arrtibuteType = "";
        boolean isCollection = false;
        if (proxyFragment != null && !proxyFragment.isEmpty()) {

            String collectionType = "";
            if (proxyFragment.contains("java.util.List")) {
                isCollection = true;
                collectionType = "java.util.List";
            } else if (proxyFragment.contains("java.util.ArrayList")) {
                isCollection = true;
                collectionType = "java.util.ArrayList";
            }


            if (isCollection) {
                int startIndex = proxyFragment.indexOf(collectionType + "[project^id=");
                startIndex += (collectionType + "[project^id=").length();
                String temp = arrtibuteType =
                        proxyFragment.substring(startIndex, proxyFragment.indexOf("]$uml.Interface"));
                arrtibuteType = collectionType + "<" + temp + ">";

            } else {

                int startIndex = proxyFragment.indexOf(collectionType + "[project^id=");
                startIndex += ("[project^id=").length();

                arrtibuteType =
                        proxyFragment.substring(startIndex, proxyFragment.indexOf("]$uml.Interface"));
            }


        }

        return arrtibuteType;
    }

}
