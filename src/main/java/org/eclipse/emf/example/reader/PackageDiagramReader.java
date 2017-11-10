package org.eclipse.emf.example.reader;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.example.models._class.ClassStructure;
import org.eclipse.emf.example.models._package.PackageDiagram;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PackageDiagramReader implements Serializable {

    private static final long serialVersionUID = 1L;

    public static List<PackageDiagram> getRefModelDetails(Package _package) {
        List<PackageDiagram> packages = new ArrayList<>();
        EList<PackageableElement> packageableElements;

        if (_package != null) {
            packageableElements = _package.getPackagedElements();
        } else {
            System.err.println("Package is null");
            return null;
        }

        for (PackageableElement element : packageableElements) {

            if (element.eClass() == UMLPackage.Literals.PACKAGE) {
                _package = (Package) element;
                PackageDiagram pd = new PackageDiagram();
                pd.setPackageName(_package.getName());
                for (Package importPackage : _package.getImportedPackages()) {
                    pd.addImport(importPackage.getName());
                }
                for (PackageableElement elecment2 : _package
                        .getPackagedElements()) {
                    if (elecment2.eClass() == UMLPackage.Literals.CLASS) {
                        Class _class = (Class) elecment2;
                        ClassStructure classStructure = new ClassStructure();
                        classStructure.setName(_class.getName());
                        classStructure.setPackage(pd.getPackageName());
                        classStructure.setImports(pd.getImports());
                        pd.addClass(classStructure);
                    }

                }
                packages.add(pd);
            }
        }
        return packages;
    }
}