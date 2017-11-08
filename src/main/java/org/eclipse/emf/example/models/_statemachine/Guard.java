package org.eclipse.emf.example.models._statemachine;

public class Guard {

    private String name;
    private String body;
    private String language;


    public Guard() {
    }

    public Guard(String name, String body, String language) {
        this.name = name;
        this.body = body;
        this.language = language;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


}
