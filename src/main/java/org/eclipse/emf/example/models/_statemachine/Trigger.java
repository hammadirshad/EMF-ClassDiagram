package org.eclipse.emf.example.models._statemachine;

import java.util.ArrayList;

public class Trigger {

    private String opName;
    private ArrayList<String> opParameters;
    private ArrayList<String> parametersClass;


    public Trigger() {
    }


    public Trigger(String opName, ArrayList<String> opParameters,
                   ArrayList<String> parametersClass) {
        this.opName = opName;
        this.opParameters = opParameters;
        this.parametersClass = parametersClass;
    }


    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public ArrayList<String> getOpParameters() {
        return opParameters;
    }

    public void setOpParameters(ArrayList<String> opParameters) {
        this.opParameters = opParameters;
    }

    public ArrayList<String> getParametersClass() {
        return parametersClass;
    }

    public void setParametersClass(ArrayList<String> parametersClass) {
        this.parametersClass = parametersClass;
    }
}
