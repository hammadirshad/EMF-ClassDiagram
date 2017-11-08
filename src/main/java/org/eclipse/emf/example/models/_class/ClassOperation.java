package org.eclipse.emf.example.models._class;

import java.util.ArrayList;
import java.util.List;

public class ClassOperation {
    private String name;
    private OperationReturn returnType;
    private String visibility;
    private List<OperationParameter> parameters = new ArrayList<>();
    private String body;
    private boolean _abstract;
    private boolean _final;
    private String oNonConditionBody = "";
    private String condition = "";
    private String conditionTrue_Body = "";
    private String conditionFalse_Body = "";


    public void addParameter(OperationParameter parameter) {
        this.parameters.add(parameter);
    }

    public String getConditionFalse_Body() {
        return conditionFalse_Body;
    }

    public void setConditionFalse_Body(String conditionFalse_Body) {
        this.conditionFalse_Body = conditionFalse_Body;
    }

    public String getConditionTrue_Body() {
        return conditionTrue_Body;
    }

    public void setConditionTrue_Body(String conditionTrue_Body) {
        this.conditionTrue_Body = conditionTrue_Body;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getoNonConditionBody() {
        return oNonConditionBody;
    }

    public void setoNonConditionBody(String oNonConditionBody) {
        this.oNonConditionBody = oNonConditionBody;
    }

    public boolean isAbstract() {
        return _abstract;
    }

    public void setAbstract(boolean _abstract) {
        this._abstract = _abstract;
    }

    public boolean isFinal() {
        return _final;
    }

    public void setFinal(boolean _final) {
        this._final = _final;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<OperationParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<OperationParameter> parameters) {
        this.parameters = parameters;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public OperationReturn getReturnType() {
        return returnType;
    }

    public void setReturnType(OperationReturn returnType) {
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
