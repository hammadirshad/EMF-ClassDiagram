package org.eclipse.emf.example.reader.diagram;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.example.models._class.ClassDiagram;
import org.eclipse.emf.example.models._class.ClassInstance;
import org.eclipse.emf.example.models._class.ClassStructure;
import org.eclipse.emf.example.models._enum.EnumStructure;
import org.eclipse.emf.example.models._package.PackageStructure;
import org.eclipse.emf.example.reader.PackageReader;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassDiagramReader implements Serializable {
    private static final long serialVersionUID = 1L;

    public static ClassDiagram getRefModelDetails(Package _package) {

        ClassDiagram classDiagram = new ClassDiagram();

        PackageStructure packageStructure;
        if (_package != null) {
            EList<PackageableElement> packageableElements = _package.getPackagedElements();
            String packageName = _package.getName() != null ? _package.getName() : "";
            packageStructure = PackageReader.readPackage(packageableElements, packageName);
        } else {
            System.err.println("Package is null");
            return null;
        }

        Map<String, ClassStructure> classes = classStructures(packageStructure);
        for (ClassStructure cs : classes.values()) {
            List<ClassStructure> superClasses = new ArrayList<>();
            for (ClassStructure superClass : cs.getSuperClasses()) {
                superClasses.add(classes.get(superClass.getName()));
            }
            cs.setSuperClasses(superClasses);


        }

        Map<String, ClassInstance> instances = classInstances(packageStructure);

        for (ClassInstance classInstance : instances.values()) {
            for (ClassStructure classStructure : classInstance.getClasses()) {
                classes.get(classStructure.getName()).getInstances().add(classInstance);
            }
        }

        classDiagram.getEnumerations().addAll(enumStructure(packageStructure).values());
        classDiagram.getClasses().addAll(classes.values());
        classDiagram.getInstances().addAll(instances.values());

        return classDiagram;
    }

    private static Map<String, ClassInstance> classInstances(PackageStructure packageStructure) {
        Map<String, ClassInstance> instances = new HashMap<>();

        for (ClassInstance classInstance : packageStructure.getInstances()) {
            instances.put(classInstance.getName(), classInstance);
        }

        for (PackageStructure ps : packageStructure.getPackages()) {
            instances.putAll(classInstances(ps));
        }
        return instances;
    }

    private static Map<String, ClassStructure> classStructures(PackageStructure packageStructure) {
        Map<String, ClassStructure> classes = new HashMap<>();

        for (ClassStructure classStructure : packageStructure.getClasses()) {
            classes.put(classStructure.getName(), classStructure);
        }

        for (PackageStructure ps : packageStructure.getPackages()) {
            classes.putAll(classStructures(ps));
        }
        return classes;
    }


    private static Map<String, EnumStructure> enumStructure(PackageStructure packageStructure) {
        Map<String, EnumStructure> enums = new HashMap<>();

        for (EnumStructure classStructure : packageStructure.getEnums()) {
            enums.put(classStructure.getName(), classStructure);
        }

        for (PackageStructure ps : packageStructure.getPackages()) {
            enums.putAll(enumStructure(ps));
        }
        return enums;
    }


}
