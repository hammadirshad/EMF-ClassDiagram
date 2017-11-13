package org.eclipse.emf.example.reader.diagram;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.example.models._usecase.UseCaseDiagram;
import org.eclipse.emf.example.util.Keywords;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.impl.PropertyImpl;
import org.eclipse.uml2.uml.internal.impl.UseCaseImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UseCaseDiagramReader implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * This function inputs a reference model and return a hashtable containing
     * use case model details (i.e. actors, usecase name, associations)
     */
    public static UseCaseDiagram getRefModelDetails(Package _package) {
        UseCaseDiagram ud = new UseCaseDiagram();
        EList<PackageableElement> packageableElements;

        if (_package != null) {
            packageableElements = _package.getPackagedElements();
        } else {
            System.err.println("Package is null");
            return null;
        }

        List<String> actors = new ArrayList<>();
        List<String> usecases = new ArrayList<>();
        List<String> associations = new ArrayList<>();

        for (PackageableElement element : packageableElements) {
            if (element.eClass() == UMLPackage.Literals.ACTOR)
                actors.add(element.getName());
            else if (element.eClass() == UMLPackage.Literals.USE_CASE)
                usecases.add(element.getName());
            if (element.eClass() == UMLPackage.Literals.COMPONENT) {
                EList<Element> ownedElems = element.getOwnedElements();
                for (Element elem : ownedElems) {
                    if (elem.getClass().getName()
                            .contains(Keywords.UseCaseImpl)) {
                        UseCaseImpl uc = (UseCaseImpl) elem;
                        usecases.add(uc.getName());
                    }
                }

            } else if (element.eClass() == UMLPackage.Literals.ASSOCIATION) {
                EList<Element> ownedElems = element
                        .getOwnedElements();
                for (Element elem : ownedElems) {
                    PropertyImpl pi = (PropertyImpl) elem;
                    associations.add(pi.getName());
                }

            }
        }
        ud.setActors(actors);
        ud.setUsecases(usecases);
        ud.setAssociations(associations);
        return ud;
    }

}