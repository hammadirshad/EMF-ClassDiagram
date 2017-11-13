package org.eclipse.emf.example.reader;

import org.eclipse.emf.example.models._enum.EnumStructure;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.PackageableElement;

public class EnumerationReader {
    public static EnumStructure readEnumeration(PackageableElement element, String packageName) {

        EnumStructure structure = new EnumStructure();
        Enumeration enumeration = (Enumeration) element;
        structure.setName(enumeration.getName());
        structure.setPackage(packageName);
        for (EnumerationLiteral literal : enumeration.getOwnedLiterals()) {
            structure.addLiteral(literal.getName());
        }

        return structure;
    }
}
