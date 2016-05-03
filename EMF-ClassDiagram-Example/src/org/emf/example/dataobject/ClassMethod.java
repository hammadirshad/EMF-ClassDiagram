package org.emf.example.dataobject;

import java.util.ArrayList;

public class ClassMethod {
    private String methodName;
    private MethodReturn methodReturnType;
    private String methodVisibility;
    private ArrayList<ClassAttribute> methodParameters;
    private String methodBody;

    private String oNonConditionBody = "";
    private String condition = "";
    private String conditionTrue_Body = "";
    private String conditionFalse_Body = "";

    public ClassMethod() {
        methodName = "";
        methodReturnType = new MethodReturn();
        methodVisibility = "";
        methodBody = "";
        methodParameters = new ArrayList<ClassAttribute>();
    }

    public ClassMethod(String methodName, MethodReturn methodReturnType,
                       String methodAccessibility,
                       ArrayList<ClassAttribute> methodParameters, String methodBody) {
        this.methodName = methodName;
        this.methodReturnType = methodReturnType;
        this.methodVisibility = methodAccessibility;
        this.methodBody = methodBody == null ? "" : methodBody;
        this.methodParameters = methodParameters == null ? new ArrayList<ClassAttribute>()
                : methodParameters;
    }

    public String printMethodEmpty() {
        String method = "";
        method += (this.methodVisibility + " " + this.methodReturnType + " "
                + this.methodName + "(");
        for (ClassAttribute param : this.methodParameters) {
            method += (param.printAttribute() + " ");
        }
        method += "){ \n }";
        return method;
    }

    public String printMethod() {
        String method = "";
        method += (this.methodVisibility + " " + this.methodReturnType + " "
                + this.methodName + "(");
        for (ClassAttribute param : this.methodParameters) {
            method += (param.printAttribute() + " ");
        }
        method += "){\n" + this.methodBody + "\n}";
        return method;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public MethodReturn getMethodReturnType() {
        return methodReturnType;
    }

    public void setMethodReturnType(MethodReturn methodReturnType) {
        this.methodReturnType = methodReturnType;
    }

    public String getMethodVisibility() {
        return methodVisibility;
    }

    public void setMethodVisibility(String methodVisibility) {
        this.methodVisibility = methodVisibility;
    }

    public ArrayList<ClassAttribute> getMethodParameters() {
        return methodParameters;
    }

    public void setMethodParameters(ArrayList<ClassAttribute> methodParameters) {
        this.methodParameters = methodParameters;
    }

    public String getMethodBody() {
        return methodBody;
    }

    public void setMethodBody(String methodBody) {
        this.methodBody = methodBody;
    }

    public String getoNonConditionBody() {
        return oNonConditionBody;
    }

    public void setoNonConditionBody(String oNonConditionBody) {
        this.oNonConditionBody = oNonConditionBody;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getConditionTrue_Body() {
        return conditionTrue_Body;
    }

    public void setConditionTrue_Body(String conditionTrue_Body) {
        this.conditionTrue_Body = conditionTrue_Body;
    }

    public String getConditionFalse_Body() {
        return conditionFalse_Body;
    }

    public void setConditionFalse_Body(String conditionFalse_Body) {
        this.conditionFalse_Body = conditionFalse_Body;
    }


}
