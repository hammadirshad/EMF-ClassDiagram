package org.eclipse.emf.example.reader.diagram;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.example.models._statemachine.Effect;
import org.eclipse.emf.example.models._statemachine.Guard;
import org.eclipse.emf.example.models._statemachine.StateMachine;
import org.eclipse.emf.example.models._statemachine.TransitionDetails;
import org.eclipse.uml2.uml.*;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;

import java.util.ArrayList;
import java.util.List;


public class StateMachineReader {


    public List<StateMachine> getRefModelDetails(Package _package) {

        EList<PackageableElement> packageableElements;

        if (_package != null) {
            packageableElements = _package.getPackagedElements();
        } else {
            System.err.println("Package is null");
            return null;
        }
        List<StateMachine> stateMachineDetails = new ArrayList<>();


        for (PackageableElement element : packageableElements) {
            if (element.eClass() == UMLPackage.Literals.CLASS) {
                Class c = (Class) element;
                EList<Behavior> ownedBehaviors = c.getOwnedBehaviors();
                for (Behavior beh : ownedBehaviors) {
                    if (beh.eClass() == UMLPackage.Literals.STATE_MACHINE) {

                        //System.out.println("Class Name : "+parentClassName);
                        //if (element.eClass() == UMLPackage.Literals.STATE_MACHINE) {
                        org.eclipse.uml2.uml.StateMachine stateMachine = (org.eclipse.uml2.uml.StateMachine) beh;
                        //System.out.println("Parent  :  "+parentClassName);

                        EList<Region> regions = stateMachine.getRegions();
                        for (Region reg : regions) {
                            //System.out.println("Region : " + reg.getLabel());
                            EList<Vertex> vertices = reg.getSubvertices();

                            for (Vertex vertex : vertices) {
                                StateMachine smDetails = new StateMachine();
                                if (vertex.eClass() == UMLPackage.Literals.STATE) {
                                    //		System.out.println("Vertex : "+vertex.getName());

                                    smDetails.setName(vertex.getLabel());
                                    ArrayList<TransitionDetails> transition = new ArrayList<>();
                                    transition = readVertices(vertex, smDetails);
                                    smDetails.setTransitions(transition);
                                    stateMachineDetails.add(smDetails);

                                }
                            }

                        }

//			EList<Behavior> ownedBehaviors = c.getOwnedBehaviors();
//			for (Behavior beh : ownedBehaviors) {
//				if (beh.eClass() == UMLPackage.Literals.STATE_MACHINE){
//					System.out.println(beh.getName());
//					readBehaviours(beh);
                    }
                }
            }

        }

        return stateMachineDetails;
    }


    public static ArrayList<TransitionDetails> readVertices(Vertex vertex, StateMachine smDetails) {
        State state = (State) vertex;
        ArrayList<TransitionDetails> transition = new ArrayList<>();
        EList<Transition> outgoingTransitions = state.getOutgoings();
        for (Transition trans : outgoingTransitions) {

            if (state.eClass() != UMLPackage.Literals.PSEUDOSTATE) {
                //System.out.println("Source : "+trans.getSource().getLabel());
                //System.out.println("Dest : "+trans.getTarget().getLabel());
                TransitionDetails temp = transitionDetails(trans);
                temp.setName(trans.getLabel());
                temp.setDest(trans.getTarget().getLabel());
                transition.add(temp);

            }
        }
        return transition;
    }

    public static TransitionDetails transitionDetails(Transition trans) {
        String condition = "";
        TransitionDetails transition = new TransitionDetails();

        ///****** guard condition ***********************
        EList<Constraint> ownedRules = trans.getOwnedRules();
        Guard guard = new Guard();
        for (Constraint Rule : ownedRules) {

            ValueSpecification Specifications = Rule.getSpecification();
            guard.setName(Rule.getLabel());
            OpaqueExpression expr = (OpaqueExpression) Specifications;

            condition += expr.getBodies().toString();
            guard.setBody(removeSquareBrackets(condition));
            //condition = expr.getLanguages();
            //	System.out.println("Condition : "+condition);
        }
        transition.setGuard(guard);

        Effect effect = new Effect();
        String methodBody = null;
        if ((OpaqueBehavior) trans.getEffect() != null) {
            methodBody = "";
            effect.setName(trans.getEffect().getLabel());
            //methodBody += ("\nif ( "+removeSquareBrackets(condition)+" ){\n");
            methodBody += removeSquareBrackets(((OpaqueBehavior) trans.getEffect()).getBodies().toString());
            //System.out.println("Effect : "+methodBody);
            effect.setBody(methodBody);

        }

        //Trigger yet to read//

        //
        transition.setEffect(effect);
    /*		TimeEvent timeEvent=null;
            ChangeEvent changeEvent = null;
	*/
        //Triggers reading
        CallEvent callEvent = null;
        Operation operation = null;
        EList<Trigger> trigger = trans.getTriggers();
        org.eclipse.emf.example.models._statemachine.Trigger trig = new org.eclipse.emf.example.models._statemachine.Trigger();
        for (Trigger triger : trigger) {
            //System.out.println("Triger : "+triger.getQualifiedName());
            //if(triger.getEvent().getName().contains("CallEvent")){
            callEvent = (CallEvent) (triger.getEvent());
            operation = callEvent.getOperation();

            if (operation != null) {

                //System.out.println("Operation : "+operation.getLabel());
                EList<Parameter> parameters = operation.getOwnedParameters();
                ArrayList<String> param = new ArrayList<>();
                ArrayList<String> paramClass = new ArrayList<>();


                for (Parameter pm : parameters) {
                    param.add(pm.getLabel());
                    paramClass.add(pm.getClass().getName());
                    //System.out.println("Parameters : "+pm.getLabel());
                }
                trig.setOpName(operation.getLabel());
                trig.setOpParameters(param);
                trig.setParametersClass(paramClass);
            }


        }
        transition.setTrigger(trig);
        //}
        return transition;
    }


    private static String removeSquareBrackets(String myString) {
        //		print("remove braces : "+myString);
        if (myString.equals(""))
            return myString;
        return myString.substring(1, myString.length() - 1);
    }

    public static void printStateMachine(ArrayList<StateMachine> stateMachineDetails) {
        for (StateMachine details : stateMachineDetails) {
            System.out.println("State Name : " + details.getName());
            ArrayList<TransitionDetails> trDetails = details.getTransitions();
            for (TransitionDetails trDetail : trDetails) {

                System.out.println("Transition Name : " + trDetail.getName());
                System.out.println("Dest : " + trDetail.getDest());
                System.out.println("Effect name : " + trDetail.getEffect().getName());
                System.out.println("Effect body : " + trDetail.getEffect().getBody());
                System.out.println("Guard name : " + trDetail.getGuard().getName());
                System.out.println("Guard body : " + trDetail.getGuard().getBody());

                System.out.println("Trigger Name : " + trDetail.getTrigger().getOpName());
            }
        }
    }

}
