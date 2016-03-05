package org.emf.example.driver;

import java.io.File;

import org.emf.example.dataobject.ClassDiagram;
import org.emf.example.dataobject.ClassStructure;
import org.emf.example.reader.ClassDiagramReader;

public class Driver {
	public static void main(String args[]) throws Exception {
		File model = new File("model/UML.uml");
		ClassDiagram cd = new ClassDiagramReader().getRefModelDetails(model);
		for (ClassStructure cs : cd.getClasses()) {
			System.out.println(cs.getClassName());
		}
	}
}
