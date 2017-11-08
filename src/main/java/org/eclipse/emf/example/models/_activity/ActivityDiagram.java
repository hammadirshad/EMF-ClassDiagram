package org.eclipse.emf.example.models._activity;

import java.util.ArrayList;

public class ActivityDiagram {
    private String activityName;
    private ArrayList<String> decisions;
    private ArrayList<String> merges;
    private ArrayList<String> forks;
    private ArrayList<String> joins;
    private ArrayList<String> opaqueActions;
    private ArrayList<String> edges;


    public ActivityDiagram() {
        activityName = null;
        decisions = new ArrayList<>();
        merges = new ArrayList<>();
        forks = new ArrayList<>();
        joins = new ArrayList<>();
        opaqueActions = new ArrayList<>();
        edges = new ArrayList<>();
    }


    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public ArrayList<String> getDecisions() {
        return decisions;
    }

    public void setDecisions(ArrayList<String> decisions) {
        this.decisions = decisions;
    }

    public void addDecisions(String decision) {
        this.decisions.add(decision);
    }

    public ArrayList<String> getMerges() {
        return merges;
    }

    public void setMerges(ArrayList<String> merges) {
        this.merges = merges;
    }

    public void addMerges(String merge) {
        this.forks.add(merge);
    }

    public ArrayList<String> getForks() {
        return forks;
    }

    public void setForks(ArrayList<String> forks) {
        this.forks = forks;
    }

    public void addForks(String fork) {
        this.forks.add(fork);
    }

    public ArrayList<String> getJoins() {
        return joins;
    }

    public void setJoins(ArrayList<String> joins) {
        this.joins = joins;
    }

    public void addJoins(String join) {
        this.joins.add(join);
    }

    public ArrayList<String> getOpaqueActions() {
        return opaqueActions;
    }

    public void setOpaqueActions(ArrayList<String> opaqueActions) {
        this.opaqueActions = opaqueActions;
    }

    public void addOpaqueActions(String opaqueAction) {
        this.opaqueActions.add(opaqueAction);
    }

    public ArrayList<String> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<String> edges) {
        this.edges = edges;
    }

    public void addEdges(String edge) {
        this.edges.add(edge);
    }


}
