package org.eclipse.emf.example.models._usecase;

import java.util.ArrayList;
import java.util.List;

public class UseCaseDiagram {

    private String systemName;
    private List<String> actors;
    private List<String> usecases;
    private List<String> associations;
    private int actorsCount;
    private int usecasesCount;
    private int includesCount;
    private int extendsCount;

    public UseCaseDiagram() {
        systemName = null;
        actors = new ArrayList<>();
        usecases = new ArrayList<>();
        associations = new ArrayList<>();

        actorsCount = 0;
        usecasesCount = 0;
        includesCount = 0;
        extendsCount = 0;
    }

    public UseCaseDiagram(String systemName, List<String> actors,
                          List<String> usecases, List<String> associations,
                          int actorsCount, int usecasesCount, int includesCount,
                          int extendsCount) {
        this.systemName = systemName;
        this.actors = actors;
        this.usecases = usecases;
        this.associations = associations;
        this.actorsCount = actorsCount;
        this.usecasesCount = usecasesCount;
        this.includesCount = includesCount;
        this.extendsCount = extendsCount;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getUsecases() {
        return usecases;
    }

    public void setUsecases(List<String> usecases) {
        this.usecases = usecases;
    }

    public List<String> getAssociations() {
        return associations;
    }

    public void setAssociations(List<String> associations) {
        this.associations = associations;
    }

    public int getActorsCount() {
        return actorsCount;
    }

    public void setActorsCount(int actorsCount) {
        this.actorsCount = actorsCount;
    }

    public int getUsecasesCount() {
        return usecasesCount;
    }

    public void setUsecasesCount(int usecasesCount) {
        this.usecasesCount = usecasesCount;
    }

    public int getIncludesCount() {
        return includesCount;
    }

    public void setIncludesCount(int includesCount) {
        this.includesCount = includesCount;
    }

    public int getExtendsCount() {
        return extendsCount;
    }

    public void setExtendsCount(int extendsCount) {
        this.extendsCount = extendsCount;
    }
}
