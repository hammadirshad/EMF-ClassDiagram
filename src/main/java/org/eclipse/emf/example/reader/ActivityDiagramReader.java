package org.eclipse.emf.example.reader;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.example.models._activity.ActivityDiagram;
import org.eclipse.uml2.uml.*;
import org.eclipse.uml2.uml.Package;

public class ActivityDiagramReader {

    public ActivityDiagram getRefModelDetails(Package _package) {
        ActivityDiagram adDetails = new ActivityDiagram();

        EList<PackageableElement> packageableElements;

        if (_package != null) {
            packageableElements = _package.getPackagedElements();
        } else {
            System.err.println("Package is null");
            return null;
        }

        for (PackageableElement element : packageableElements) {
            if (element.eClass() == UMLPackage.Literals.ACTIVITY) {
                Activity activity = (Activity) element;
                adDetails.setActivityName(activity.getName());
                for (ActivityNode an : activity.getNodes()) {
                    if (an.eClass() == UMLPackage.Literals.OPAQUE_ACTION) {
                        adDetails.addOpaqueActions(an.getName());
                    } else if (an.eClass() == UMLPackage.Literals.JOIN_NODE) {
                        adDetails.addJoins(an.getName());
                    } else if (an.eClass() == UMLPackage.Literals.FORK_NODE) {
                        adDetails.addForks(an.getName());
                    } else if (an.eClass() == UMLPackage.Literals.MERGE_NODE) {
                        adDetails.addMerges(an.getName());
                    } else if (an.eClass() == UMLPackage.Literals.DECISION_NODE) {
                        adDetails.addDecisions(an.getName());
                    }
                }
                for (ActivityEdge ed : activity.getEdges()) {
                    adDetails
                            .addEdges(ed.getSource().getName() + ","
                                    + ed.getName() + ","
                                    + ed.getTarget().getName());
                }
            }

        }

        return adDetails;
    }

}
