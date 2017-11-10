package org.eclipse.emf.example.models._activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityDiagram {
    private String activityName;
    private List<String> decisions= new ArrayList<>();
    private List<String> merges= new ArrayList<>();
    private List<String> forks= new ArrayList<>();
    private List<String> joins= new ArrayList<>();
    private List<String> opaqueActions= new ArrayList<>();
    private List<String> edges= new ArrayList<>();


    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public List<String> getDecisions() {
        return decisions;
    }

    public void setDecisions(List<String> decisions) {
        this.decisions = decisions;
    }

    public List<String> getMerges() {
        return merges;
    }

    public void setMerges(List<String> merges) {
        this.merges = merges;
    }

    public List<String> getForks() {
        return forks;
    }

    public void setForks(List<String> forks) {
        this.forks = forks;
    }

    public List<String> getJoins() {
        return joins;
    }

    public void setJoins(List<String> joins) {
        this.joins = joins;
    }

    public List<String> getOpaqueActions() {
        return opaqueActions;
    }

    public void setOpaqueActions(List<String> opaqueActions) {
        this.opaqueActions = opaqueActions;
    }

    public List<String> getEdges() {
        return edges;
    }

    public void setEdges(List<String> edges) {
        this.edges = edges;
    }
}
