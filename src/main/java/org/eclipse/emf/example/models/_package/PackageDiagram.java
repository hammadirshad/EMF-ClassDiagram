package org.eclipse.emf.example.models._package;

import org.eclipse.emf.example.models._class.ClassStructure;

import java.util.ArrayList;

public class PackageDiagram {
    private String packageName;
    private ArrayList<ClassStructure> classes = new ArrayList<ClassStructure>();
    private ArrayList<String> imports = new ArrayList<String>();

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public ArrayList<ClassStructure> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<ClassStructure> classes) {
        this.classes = classes;
    }

    public void addClass(ClassStructure _class) {
        this.classes.add(_class);
    }

    public ArrayList<String> getImports() {
        return imports;
    }

    public void setImports(ArrayList<String> imports) {
        this.imports = imports;
    }

    public void addImport(String _import) {
        this.imports.add(_import);
    }
}
