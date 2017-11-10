package org.eclipse.emf.example.reader;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.example.models._enum.EnumStructure;
import org.eclipse.uml2.uml.*;
import org.eclipse.uml2.uml.Package;

public class UMLProfileReader {


    public String getRefModelDetails(Package _package) {

        EList<PackageableElement> packageableElements;

        if (_package != null) {
            packageableElements = _package.getPackagedElements();

        } else {
            System.err.println("Package is null");
            return null;
        }

        String packageName = _package.getName() != null ? _package.getName() : "";

        for (PackageableElement element : packageableElements) {
            if (element.eClass() == UMLPackage.Literals.STEREOTYPE) {
                Stereotype stereotype = (Stereotype) element;

                System.out.println("Stereotype: " + stereotype.getName());

            } else if (element.eClass() == UMLPackage.Literals.EXTENSION) {
                Extension extension = (Extension) element;
                System.out.println("Extension: " + extension.getName());

            } else if (element.eClass() == UMLPackage.Literals.ENUMERATION) {
                System.out.println("Enumeration: " + element.getName());
                EnumStructure enumStructure = ClassDiagramReader.readEnumeration(element, packageName);

            } else {
                System.out.println(element.eClass());
            }

        }

        return null;
    }

}
