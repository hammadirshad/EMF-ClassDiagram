package org.eclipse.emf.example.models._enum;

import java.util.ArrayList;
import java.util.List;

public class EnumStructure {
    private String _package;
    private String name;
    private List<String> literals = new ArrayList<String>();

    public String getPackage() {
        return _package;
    }

    public void setPackage(String _package) {
        this._package = _package;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLiterals() {
        return literals;
    }

    public void setLiterals(List<String> literals) {
        this.literals = literals;
    }

    public void addLiteral(String literal) {
        this.literals.add(literal);
    }


}
