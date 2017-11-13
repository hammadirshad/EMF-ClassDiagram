package org.eclipse.emf.example.driver;


import org.eclipse.emf.example.loader.ModelLoader;
import org.eclipse.emf.example.models._activity.ActivityDiagram;
import org.eclipse.emf.example.models._class.ClassDiagram;
import org.eclipse.emf.example.models._class.ClassStructure;
import org.eclipse.emf.example.models._enum.EnumStructure;
import org.eclipse.emf.example.models._package.PackageDiagram;
import org.eclipse.emf.example.models._profile.ExtensionStructure;
import org.eclipse.emf.example.models._profile.ProfileDiagram;
import org.eclipse.emf.example.models._profile.StereotypeStructure;
import org.eclipse.emf.example.models._sequence.SequenceDiagram;
import org.eclipse.emf.example.models._sequence.SequenceMessage;
import org.eclipse.emf.example.models._statemachine.StateMachine;
import org.eclipse.emf.example.models._usecase.UseCaseDiagram;
import org.eclipse.emf.example.reader.diagram.*;
import org.eclipse.uml2.uml.Package;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Driver {
    public static void main(String args[]) throws Exception {
        File model = new File("src/main/resources/model/UML.uml");
        classDiagramReader(model);
        packageDiagramReader(model);
        activityDiagramReader(model);
        sequenceDiagramReader(model);
        stateMachineReader(model);
        useCaseDiagramReader(model);
        profileReader(model);
    }

    public static void classDiagramReader(File model) throws IOException {
        Package aPackage = new ModelLoader().loadModel(model);
        ClassDiagram classDiagram = ClassDiagramReader.getRefModelDetails(aPackage);
        if (classDiagram != null) {
            for (ClassStructure cs : classDiagram.getClasses()) {
                System.out.println("Class: " + cs.getPackage() + "." + cs.getName());
            }

            for (EnumStructure enumStructure : classDiagram.getEnumerations()) {
                System.out.println("Enumeration: " + enumStructure.getPackage() + "." + enumStructure.getName());
            }
        }
    }


    public static void packageDiagramReader(File model) throws IOException {
        Package aPackage = new ModelLoader().loadModel(model);
        PackageDiagram packageDiagram = PackageDiagramReader.getRefModelDetails(aPackage);

        if (packageDiagram != null) {
            System.out.println(packageDiagram.getPackageStructure().getName());
        }
    }


    public static void activityDiagramReader(File model) throws IOException {
        Package aPackage = new ModelLoader().loadModel(model);
        ActivityDiagram activityDiagram = ActivityDiagramReader.getRefModelDetails(aPackage);
        if (activityDiagram != null) {
            System.out.println(activityDiagram.getActivityName());
            for (String edges : activityDiagram.getEdges()) {
                System.out.println(edges);
            }
        }
    }


    public static void sequenceDiagramReader(File model) throws IOException {
        Package aPackage = new ModelLoader().loadModel(model);
        SequenceDiagram sequenceDiagram = SequenceDiagramReader.getRefModelDetails(aPackage);
        if (sequenceDiagram != null) {
            for (SequenceMessage sequenceMessage : sequenceDiagram.getMessages()) {
                System.out.println(sequenceMessage.getMessageName());
            }
        }
    }


    public static void stateMachineReader(File model) throws IOException {
        Package aPackage = new ModelLoader().loadModel(model);
        List<StateMachine> stateMachines = new StateMachineReader().getRefModelDetails(aPackage);
        for (StateMachine stateMachine : stateMachines) {
            System.out.println(stateMachine.getName());
        }
    }

    public static void useCaseDiagramReader(File model) throws IOException {
        Package aPackage = new ModelLoader().loadModel(model);
        UseCaseDiagram useCaseDiagram = UseCaseDiagramReader.getRefModelDetails(aPackage);
        if (useCaseDiagram != null) {
            System.out.println(useCaseDiagram.getSystemName());
            for (String actor : useCaseDiagram.getActors()) {
                System.out.println(actor);
            }
        }
    }


    public static void profileReader(File model) throws IOException {
        Package aPackage = new ModelLoader().loadModel(model);
        ProfileDiagram profileDiagram = UMLProfileReader.getRefModelDetails(aPackage);
        if (profileDiagram != null) {

            System.out.println(profileDiagram.getName());

            for (StereotypeStructure cs : profileDiagram.getStereotypes()) {
                System.out.println("Stereotype: " + cs.getPackage() + "." + cs.getName());
            }

            for (ExtensionStructure cs : profileDiagram.getExtensions()) {
                System.out.println("Extension: " + cs.getName());
            }

            for (EnumStructure enumStructure : profileDiagram.getEnumerations()) {
                System.out.println("Enumeration: " + enumStructure.getPackage() + "." + enumStructure.getName());
            }
        }


    }

}
