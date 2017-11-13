package org.eclipse.emf.example.reader;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.example.models._class.*;
import org.eclipse.emf.example.util.Keywords;
import org.eclipse.uml2.uml.*;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.internal.impl.*;

import java.util.ArrayList;
import java.util.List;

public class ClassStructureReader {


    public static ClassStructure readClass(Element element, String packageName) {
        ClassStructure classStructure = new ClassStructure();
        Class _class = (Class) element;
        List<String> rules = new ArrayList<>();
        //System.out.println(_class.getName());


        for (Constraint constraint : _class.getOwnedRules()) {
            if (constraint.getSpecification() instanceof OpaqueExpressionImpl) {
                OpaqueExpressionImpl expressionImpl = (OpaqueExpressionImpl) constraint.getSpecification();
                rules.addAll(expressionImpl.getBodies());
            }

        }


        for (Class superClass : _class.getSuperClasses()) {

            ClassStructure superClassStructure = new ClassStructure();
            classStructure.setName(superClass.getName());
            classStructure.setPackage(superClass.getPackage().getName());
            classStructure.getSuperClasses().add(superClassStructure);

        }


        //System.out.println("\n  -------- \n");

        classStructure.setPackage(packageName);
        classStructure.setVisibility(_class.getVisibility().toString());
        classStructure.setRules(rules);
        classStructure.setAbstract(_class.isAbstract());
        classStructure.setFinal(_class.isLeaf());
        classStructure.setName(_class.getName());
        classStructure.setAttributes(readAttribute(_class.getOwnedAttributes()));
        classStructure.setOperations(readClassOperations(_class.getOwnedOperations()));
        classStructure.setRelationships(readClassRelations(_class.getRelationships()));


        for (NamedElement inheritedElement : _class.getInheritedMembers()) {

            if (inheritedElement instanceof Property) {
                Property property = (Property) inheritedElement;
                ClassAttribute attribute = readAttribute(property);
                if (attribute != null && attribute.getName() != null) {
                    classStructure.addAttribute(attribute);
                }
            } else if (inheritedElement instanceof Operation) {
                Operation operation = (Operation) inheritedElement;
                ClassOperation classOperation = readClassOperation(operation);
                if (classOperation != null) {
                    classStructure.addOperation(classOperation);
                }
            }

        }


        return classStructure;
    }


    public static ArrayList<ClassRelation> readClassRelations(EList<Relationship> classRelationships) {
        ArrayList<ClassRelation> list = new ArrayList<>();
        for (Relationship relationship : classRelationships) {
            if (relationship.eClass() == UMLPackage.Literals.ASSOCIATION) {
                list.add(readAssociation(relationship));
            } else if (relationship.eClass() == UMLPackage.Literals.GENERALIZATION) {
                list.add(readGeneralization(relationship));
            }

        }
        return list;
    }


    public static ArrayList<ClassOperation> readClassOperations(List<Operation> ownedOperations) {
        ArrayList<ClassOperation> operations = new ArrayList<>();
        if (!ownedOperations.isEmpty()) {
            for (Operation operation : ownedOperations) {

                ClassOperation classOperation = readClassOperation(operation);
                if (classOperation != null) {
                    operations.add(classOperation);
                }

            }
        }
        return operations;
    }

    public static ClassOperation readClassOperation(Operation operation) {

        ClassOperation classOperation = new ClassOperation();
        classOperation.setName(operation.getName());
        classOperation.setVisibility(operation.getVisibility().toString());
        classOperation.setReturnType(new OperationReturn());

        EList<Parameter> parameters = operation.getOwnedParameters();
        if (!parameters.isEmpty()) {
            for (Parameter parameter : parameters) {

                boolean returnType = false;


                ParameterDirectionKind direction = parameter.getDirection();
                if (direction != null && direction.getValue() == 3) {
                    returnType = true;
                }

                OperationParameter operationParameter = new OperationParameter();

                if (parameter
                        .getType() instanceof PrimitiveTypeImpl) {
                    PrimitiveTypeImpl primitiveType = (PrimitiveTypeImpl) (parameter.getType());
                    if (returnType) {
                        if (primitiveType.getName() == null || primitiveType.getName().equals("")) {
                            OperationReturn methodReturn = new OperationReturn();
                            methodReturn.setType(primitiveType.eProxyURI().fragment());
                            if (parameter.getUpper() == -1) {
                                methodReturn.setCollection(true);
                            }
                            classOperation.setReturnType(methodReturn);
                        } else {
                            OperationReturn methodReturn = new OperationReturn();
                            methodReturn.setType(primitiveType.getName());
                            if (parameter.getUpper() == -1) {
                                methodReturn.setCollection(true);
                            }
                            classOperation.setReturnType(methodReturn);

                        }

                    } else {
                        operationParameter.setName(parameter.getName());

                        if (primitiveType.getName() == null || primitiveType.getName().equals("")) {
                            operationParameter.setType(primitiveType.eProxyURI().fragment());
                            if (parameter.getUpper() == -1) {
                                operationParameter.setCollection(true);
                            }
                        } else {
                            if (parameter.getUpper() == -1) {
                                operationParameter.setCollection(true);
                            }
                            operationParameter.setType(primitiveType.getName());
                        }
                        classOperation.getParameters().add(operationParameter);
                    }
                } else if (parameter
                        .getType() instanceof Enumeration) {

                    Enumeration enumeration = (Enumeration) (parameter.getType());

                    if (returnType) {
                        OperationReturn methodReturn = new OperationReturn();
                        methodReturn.setType(enumeration.getName());
                        if (parameter.getUpper() == -1) {
                            methodReturn.setCollection(true);
                        }
                        classOperation.setReturnType(methodReturn);

                    } else {

                        operationParameter.setName(parameter.getName());
                        operationParameter.setVisibility(parameter.getVisibility().toString());
                        operationParameter.setType(enumeration.getName());
                        if (parameter.getUpper() == -1) {
                            operationParameter.setCollection(true);
                        }
                        classOperation.getParameters().add(operationParameter);
                    }

                } else if (parameter
                        .getType() instanceof Class) {
                    Class _class = (Class) (parameter.getType());
                    if (returnType) {
                        OperationReturn methodReturn = new OperationReturn();
                        methodReturn.setType(_class.getName());
                        methodReturn.setClass(true);
                        if (parameter.getUpper() == -1) {
                            methodReturn.setCollection(true);
                        }
                        classOperation.setReturnType(methodReturn);

                    } else {

                        operationParameter.setName(parameter.getName());
                        operationParameter.setVisibility(parameter.getVisibility().toString());
                        operationParameter.setType(_class.getName());
                        operationParameter.setClass(true);

                        if (parameter.getUpper() == -1) {
                            operationParameter.setCollection(true);
                        }
                        classOperation.getParameters().add(operationParameter);
                    }
                } else if (parameter
                        .getType() instanceof InterfaceImpl) {

                    InterfaceImpl prim = (InterfaceImpl) (parameter.getType());
                    URI proxy = prim.eProxyURI();
                    String proxyFragment = proxy.fragment();
                    String arrtibuteType = attributeInterface(proxyFragment);
                    operationParameter.setType(arrtibuteType);

                    if (returnType) {
                        OperationReturn methodReturn = new OperationReturn();
                        methodReturn.setType(arrtibuteType);
                        if (operationParameter.isCollection()) {
                            methodReturn.setCollection(true);
                        }
                        classOperation.setReturnType(methodReturn);

                    } else {
                        operationParameter.setType(arrtibuteType);
                        operationParameter.setName(parameter.getName());
                        operationParameter.setVisibility(parameter.getVisibility().toString());
                        classOperation.getParameters().add(operationParameter);
                    }
                }

            }
        }


        return classOperation;
    }


    public static List<ClassAttribute> readAttribute( EList<Property> ownedAttributes) {
        List<ClassAttribute> attributes = new ArrayList<>();
        if (!ownedAttributes.isEmpty()) {
            for (Property property : ownedAttributes) {
                ClassAttribute attribute = readAttribute(property);
                if (attribute != null && attribute.getName() != null) {
                    attributes.add(attribute);
                }
            }
        }
        return attributes;
    }

    public static ClassAttribute readAttribute(Property property) {

        ClassAttribute attribute = new ClassAttribute();
        if (property.getType() instanceof PrimitiveTypeImpl) {
            attribute.setName(property.getName());
            attribute.setVisibility(property.getVisibility().toString());
            PrimitiveTypeImpl prim = (PrimitiveTypeImpl) (property.getType());
            if (prim.getName() == null || prim.getName().equals("")) {
                attribute.setType(prim.eProxyURI().fragment());
            } else {
                attribute.setType(prim.getName());
            }

            if (property.getUpper() == -1) {
                attribute.setCollection(true);
            }

        } else if (property
                .getType() instanceof EnumerationImpl) {

            EnumerationImpl impl = (EnumerationImpl) (property.getType());
            attribute.setName(property.getName());
            attribute.setVisibility(property.getVisibility().toString());
            attribute.setType(impl.getName());
            attribute.setEnum(true);

            if (property.getUpper() == -1) {
                attribute.setCollection(true);
            }

        } else if (property.getType() instanceof ClassImpl) {

            ClassImpl impl = (ClassImpl) (property.getType());
            attribute.setName(property.getName());
            attribute.setVisibility(property.getVisibility().toString());
            attribute.setType(impl.getName());
            attribute.setClass(true);

            if (property.getUpper() == -1) {
                attribute.setCollection(true);
            }

        } else if (property
                .getType() instanceof InterfaceImpl) {

            InterfaceImpl prim = (InterfaceImpl) (property.getType());
            URI proxy = prim.eProxyURI();
            String proxyFragment = proxy.fragment();
            attribute.setCollection(attributeInterfaceCollection(proxyFragment));
            String arrtibuteType = attributeInterface(proxyFragment);
            attribute.setType(arrtibuteType);
            attribute.setName(property.getName());
            attribute.setVisibility(property.getVisibility().toString());

        }
        return attribute;
    }


    private static ClassRelation readGeneralization(Element element) {
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
        return relation;
    }


    private static  ClassRelation readAssociation(Element element) {
        Association association = (Association) element;

        ClassRelation relation = new ClassRelation();
        relation.setType(Keywords.Association);
        boolean first = true;
        for (Property end : association.getMemberEnds()) {
            if (end.getType() instanceof Class) {
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


                    LiteralUnlimitedNatural upperValue =
                            (LiteralUnlimitedNatural) end.getUpperValue();
                    if (upperValue != null) {
                        relation.setMultipcity_Uper_1(upperValue.getValue());
                    }

                    if (end instanceof LiteralUnlimitedNatural) {

                        LiteralUnlimitedNatural lowerValue =
                                (LiteralUnlimitedNatural) end.getLowerValue();
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

                    LiteralUnlimitedNatural upperValue =
                            (LiteralUnlimitedNatural) end.getUpperValue();
                    if (upperValue != null) {
                        relation.setMultipcity_Uper_2(upperValue.getValue());
                    }

                    if (end instanceof LiteralUnlimitedNatural) {

                        LiteralUnlimitedNatural lowerValue =
                                (LiteralUnlimitedNatural) end.getLowerValue();
                        relation.setMultipcity_Lower_2(lowerValue.getValue());
                    }

                    return relation;
                }

            }

        }


        return relation;
    }


    private static boolean attributeInterfaceCollection(String proxyFragment) {
        boolean isCollection = false;
        if (proxyFragment != null && !proxyFragment.isEmpty()) {
            if (proxyFragment.contains("java.util.List") || proxyFragment.contains("java.util.ArrayList")) {
                isCollection = true;
            }
        }

        return isCollection;
    }

    private static String attributeInterface(String proxyFragment) {
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
                String temp =
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
