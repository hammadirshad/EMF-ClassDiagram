package org.eclipse.emf.example.models._package;

import org.eclipse.emf.example.models._class.ClassStructure;

import java.util.ArrayList;
import java.util.List;

public class PackageDiagram {
    private String packageName;
    private List<ClassStructure> classes = new ArrayList<>();
    private List<String> imports = new ArrayList<>();


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void addClass(ClassStructure _class) {
        this.classes.add(_class);
    }

    public List<ClassStructure> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassStructure> classes) {
        this.classes = classes;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public void addImport(String _import) {
        this.imports.add(_import);
    }
}
