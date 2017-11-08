package org.eclipse.emf.example.models._statemachine;

public class TransitionDetails {

    private String name;
    private String dest;
    private Effect effect;
    private Guard guard;
    private Trigger trigger;


    public TransitionDetails() {
    }


    public TransitionDetails(String name, String dest, Effect effect,
                             Guard guard, Trigger trigger) {
        this.name = name;
        this.dest = dest;
        this.effect = effect;
        this.guard = guard;
        this.trigger = trigger;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public Guard getGuard() {
        return guard;
    }

    public void setGuard(Guard guard) {
        this.guard = guard;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }
}
