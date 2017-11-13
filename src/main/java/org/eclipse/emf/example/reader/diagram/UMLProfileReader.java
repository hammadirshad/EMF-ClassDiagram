package org.eclipse.emf.example.reader.diagram;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.example.models._class.ClassStructure;
import org.eclipse.emf.example.models._enum.EnumStructure;
import org.eclipse.emf.example.models._profile.ExtensionStructure;
import org.eclipse.emf.example.models._profile.ProfileDiagram;
import org.eclipse.emf.example.models._profile.StereotypeStructure;
import org.eclipse.emf.example.reader.ClassStructureReader;
import org.eclipse.emf.example.reader.EnumerationReader;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.*;
import org.eclipse.uml2.uml.Package;


public class UMLProfileReader {


    public static ProfileDiagram getRefModelDetails(Package _package) {

        ProfileDiagram profileDiagram = new ProfileDiagram();
        EList<PackageableElement> packageableElements;

        if (_package != null) {
            packageableElements = _package.getPackagedElements();
            if (_package.eClass() == UMLPackage.Literals.PROFILE) {
                profileDiagram.setName(_package.getName());
            }

        } else {
            System.err.println("Package is null");
            return null;
        }

        String packageName = _package.getName() != null ? _package.getName() : "";

        for (PackageableElement element : packageableElements) {

            if (element.eClass() == UMLPackage.Literals.STEREOTYPE) {
                Stereotype stereotype = (Stereotype) element;
                StereotypeStructure stereotypeStructure = readStereotype(stereotype, packageName);
                profileDiagram.getStereotypes().add(stereotypeStructure);
            } else if (element.eClass() == UMLPackage.Literals.EXTENSION) {
                Extension extension = (Extension) element;

                ExtensionStructure extensionStructure = new ExtensionStructure();
                extensionStructure.setName(extension.getName());

                /*System.out.println("Extension: " + extension.getName());
                System.out.println(extension.getStereotype().getName());
                System.out.println(extension.getStereotypeEnd().getName());
                System.out.println(extension.getStereotypeEnd().getType().getName());*/

                profileDiagram.getExtensions().add(extensionStructure);
            } else if (element.eClass() == UMLPackage.Literals.ENUMERATION) {
                EnumStructure enumStructure = EnumerationReader.readEnumeration(element, packageName);
                profileDiagram.getEnumerations().add(enumStructure);

            } else {
                System.out.println(element.eClass());
            }

        }

        return profileDiagram;
    }


    public static StereotypeStructure readStereotype(Element element, String packageName) {
        StereotypeStructure stereotypeStructure = new StereotypeStructure();
        Stereotype stereotype = (Stereotype) element;
        stereotypeStructure.setPackage(packageName);
        stereotypeStructure.setVisibility(stereotype.getVisibility().toString());
        stereotypeStructure.setAbstract(stereotype.isAbstract());
        stereotypeStructure.setFinal(stereotype.isLeaf());
        stereotypeStructure.setName(stereotype.getName());
        stereotypeStructure.setAttributes(ClassStructureReader.readAttribute(stereotype.getOwnedAttributes()));
        stereotypeStructure.setOperations(ClassStructureReader.readClassOperations(stereotype.getOwnedOperations()));
        stereotypeStructure.setRelationships(ClassStructureReader.readClassRelations(stereotype.getRelationships()));


        for (Class aClass : stereotype.getExtendedMetaclasses()) {
            ClassStructure classStructure = new ClassStructure();
            classStructure.setName(aClass.getName());
            stereotypeStructure.getExtendedClass().add(classStructure);
        }

        return stereotypeStructure;
    }
}
