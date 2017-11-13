package org.eclipse.emf.example.reader.diagram;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.example.models._package.PackageDiagram;
import org.eclipse.emf.example.models._package.PackageStructure;
import org.eclipse.emf.example.reader.PackageReader;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;

import java.io.Serializable;

public class PackageDiagramReader implements Serializable {

    private static final long serialVersionUID = 1L;

    public static PackageDiagram getRefModelDetails(Package _package) {

        if (_package != null) {
            EList<PackageableElement> packageableElements = _package.getPackagedElements();
            String packageName = _package.getName() != null ? _package.getName() : "";
            PackageStructure packageStructure = PackageReader.readPackage(packageableElements, packageName);

            PackageDiagram packageDiagram = new PackageDiagram();
            packageDiagram.setPackageStructure(packageStructure);
            return packageDiagram;
        } else {
            System.err.println("Package is null");
            return null;
        }

    }
}