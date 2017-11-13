package org.eclipse.emf.example.models._profile;

import org.eclipse.emf.example.models._class.ClassStructure;

import java.util.ArrayList;
import java.util.List;

public class StereotypeStructure extends ClassStructure {


    private List<ClassStructure> extendedClass=new ArrayList<>();


    public List<ClassStructure> getExtendedClass() {
        return extendedClass;
    }

    public void setExtendedClass(List<ClassStructure> extendedClass) {
        this.extendedClass = extendedClass;
    }
}
