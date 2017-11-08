package org.eclipse.emf.example.reader;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.example.models._class.*;
import org.eclipse.emf.example.models._sequence.*;
import org.eclipse.emf.example.util.Keywords;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.*;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.internal.impl.*;

import java.io.Serializable;
import java.util.ArrayList;


public class SequenceDiagramReader implements Serializable {
    private static final long serialVersionUID = 1L;

    public SequenceDiagram getRefModelDetails(Package _package) {

        EList<PackageableElement> packageableElements;

        if (_package != null) {
            packageableElements = _package.getPackagedElements();
        } else {
            System.err.println("Package is null");
            return null;
        }

        SequenceDiagram sequenceDiagram = new SequenceDiagram();
        for (PackageableElement element : packageableElements) {

            if (element.eClass() == UMLPackage.Literals.CLASS) {
                Class umlClass = (Class) element;
                ClassStructure structure = new ClassStructure();
                structure.setName(umlClass.getName());
                structure.setOperations(readOperations(umlClass
                        .getOperations()));
                structure.setAttributes(readAttributes(umlClass
                        .getAllAttributes()));
                sequenceDiagram.getClasses().add(structure);
            }
            if (element.eClass() == UMLPackage.Literals.COLLABORATION) {
                CollaborationImpl collaborationImpl = (CollaborationImpl) element;

                for (Property property : collaborationImpl.getAttributes()) {
                    SequenceAttribute attribute = new SequenceAttribute(
                            property.getName(), property.getType().getName());
                    sequenceDiagram.addAttribute(attribute);
                }

                if (collaborationImpl.getOwnedBehaviors() != null) {
                    for (Element element2 : collaborationImpl
                            .getOwnedBehaviors()) {
                        if (element2.eClass() == UMLPackage.Literals.INTERACTION) {
                            InteractionImpl interactionImpl = (InteractionImpl) element2;
                            SequenceDiagram read = interactionReader(interactionImpl);
                            sequenceDiagram.setLifelines(read.getLifelines());
                            sequenceDiagram.setMessages(read.getMessages());
                            sequenceDiagram.setGates(read.getGates());
                            sequenceDiagram.setBehaviors(read.getBehaviors());
                            sequenceDiagram.setFragments(read.getFragments());
                        }
                    }
                }
            }

            if (element.eClass() == UMLPackage.Literals.INTERACTION && element instanceof InteractionImpl) {
                InteractionImpl interactionImpl = (InteractionImpl) element;
                SequenceDiagram read = interactionReader(interactionImpl);
                sequenceDiagram.setLifelines(read.getLifelines());
                sequenceDiagram.setMessages(read.getMessages());
                sequenceDiagram.setGates(read.getGates());
                sequenceDiagram.setBehaviors(read.getBehaviors());
                sequenceDiagram.setFragments(read.getFragments());
            }

        }

        return sequenceDiagram;
    }


    /**
     * UML Interactions Reader read interaction messages, lifeLines,Formal
     * gates,MessageOccurrence,CombinedFragment,BehaviorExecution
     *
     * @param interactionImpl InteractionImpl
     * @return Class object that have list of messages, lifeLines,Formal
     * gates,MessageOccurrence,CombinedFragment,BehaviorExecution
     */

    private SequenceDiagram interactionReader(InteractionImpl interactionImpl) {
        SequenceDiagram sd = new SequenceDiagram();

        // reading behaviors
        for (InteractionFragment interactionFragment : interactionImpl
                .getFragments()) {
            if (interactionFragment instanceof BehaviorExecutionSpecificationImpl) {
                BehaviorExecutionSpecificationImpl fragment = (BehaviorExecutionSpecificationImpl) interactionFragment;
                sd.addBehavior(behaviorReader(fragment));
            }
        }

        // reading combine fragments
        for (InteractionFragment interactionFragment : interactionImpl
                .getFragments()) {
            if (interactionFragment instanceof CombinedFragment) {
                CombinedFragment combinedFragment = (CombinedFragment) interactionFragment;
                sd.addFragment(fragmentReader(combinedFragment));
            }
        }

        // Adding Behavior Calls
        for (SequenceBehavior behavior : sd.getBehaviors()) {
            addBehaviorDetail(behavior, interactionImpl.getFragments());
        }

        // FormalGates
        for (Gate gate : interactionImpl.getFormalGates()) {
            sd.addGate(gateReader(gate));
        }

        // LifLines
        for (Lifeline lifeline : interactionImpl.getLifelines()) {
            sd.addLifeline(lifelineReader(lifeline));
        }

        // Messages
        for (Message message : interactionImpl.getMessages()) {
            sd.addMessage(messageReader(message));
        }

        return sd;

    }

    private void addBehaviorDetail(SequenceBehavior behavior,
                                   EList<InteractionFragment> fragments) {
        ArrayList<String> calles = new ArrayList<>();
        // MessageOccurrence
        for (InteractionFragment interactionFragment : fragments) {
            if (interactionFragment instanceof MessageOccurrenceSpecificationImpl) {
                MessageOccurrenceSpecificationImpl fragment = (MessageOccurrenceSpecificationImpl) interactionFragment;
                SequenceLifeline lifeline = lifelineReader(fragment
                        .getCovereds().get(0));

                if (behavior.getLifeline().getLifelineName()
                        .equals(lifeline.getLifelineName())) {

                    SequenceMessage message = messageReader(fragment
                            .getMessage());
                    if (message != null) {

                        calles.add(message.getMessageName());

                        // Break Loop if found Synchronized Message Reply
                        if (behavior.getFinish() != null
                                && message.getMessageType() != null) {
                            if (behavior.getFinish().getMessageName()
                                    .equals(message.getMessageName())
                                    && message.getMessageType().equals(
                                    Keywords.Reply)) {
                                break;
                            }
                        }

                        // All Messages After Start Message
                        if (!behavior.getStart().getMessageName()
                                .equals(message.getMessageName())) {

                            // If Message Type is not Reply
                            if (message.getMessageType() != null) {
                                if (!message.getMessageType().equals(
                                        Keywords.Reply)) {

                                    if (message.getReciver() != null) {
                                        // Message Next Behavior Covered
                                        // Lifeline
                                        if (!message
                                                .getReciver()
                                                .getLifelineName()
                                                .equals(lifeline
                                                        .getLifelineName())) {

                                            if (calles.contains(behavior
                                                    .getStart()
                                                    .getMessageName())) {
                                                behavior.addCall(message);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (interactionFragment instanceof CombinedFragment) {
                CombinedFragment combinedFragment = (CombinedFragment) interactionFragment;
                SequenceCombinedFragment fragment = fragmentReader(combinedFragment);
                if (fragment != null) {
                    for (SequenceLifeline lifeline : fragment
                            .getSequenceLifelines()) {
                        if (lifeline.getLifelineName().equals(
                                behavior.getLifeline().getLifelineName())) {
                            behavior.addFragment(fragment);
                        }
                    }
                }
            }

        }

    }

    /**
     * fragmentReader read fragment type,condition,messages
     *
     * @param combinedFragment CombinedFragment
     * @return SequenceCombinedFragment
     */

    private SequenceCombinedFragment fragmentReader(
            CombinedFragment combinedFragment) {

        SequenceCombinedFragment sequenceCombinedFragment = new SequenceCombinedFragment();
        for (Lifeline lifeline : combinedFragment.getCovereds()) {
            SequenceLifeline fragmentLifeline = lifelineReader(lifeline);
            sequenceCombinedFragment.addSequenceLifeline(fragmentLifeline);

        }

        sequenceCombinedFragment.setOperation(combinedFragment
                .getInteractionOperator().getName());
        for (InteractionOperand interactionOperand : combinedFragment
                .getOperands()) {
            OpaqueExpressionImpl opaqueExpressionImpl = (OpaqueExpressionImpl) interactionOperand
                    .getGuard().getSpecification();
            sequenceCombinedFragment.setCondition(opaqueExpressionImpl
                    .getBodies().get(0));

            for (InteractionFragment Operandfragment : interactionOperand
                    .getFragments()) {
                if (Operandfragment instanceof MessageOccurrenceSpecificationImpl) {
                    MessageOccurrenceSpecificationImpl fragment = (MessageOccurrenceSpecificationImpl) Operandfragment;

                    SequenceMessage message = messageReader(fragment
                            .getMessage());

                    if (!message.getSender().getLifelineName().equals("")) {
                        sequenceCombinedFragment.addCall(message);
                    }
                }
            }

        }

        return sequenceCombinedFragment;
    }

    /**
     * behaviorReader read BehaviorFragment Lifeline and start and finsh event
     *
     * @param fragment BehaviorExecutionSpecificationImpl
     * @return SequenceBehavior
     */
    private SequenceBehavior behaviorReader(
            BehaviorExecutionSpecificationImpl fragment) {
        SequenceBehavior behavior = new SequenceBehavior();
        for (Lifeline lifeline : fragment.getCovereds()) {
            behavior.setLifeline(lifelineReader(lifeline));
        }
        // Behavior Start Occurrence
        if (fragment.getStart() instanceof MessageOccurrenceSpecification) {
            MessageOccurrenceSpecification specification = (MessageOccurrenceSpecification) fragment
                    .getStart();
            behavior.setStart(messageReader(specification.getMessage()));
        }
        // Behavior Finish Occurrence
        if (fragment.getFinish() instanceof MessageOccurrenceSpecification) {
            MessageOccurrenceSpecification specification = (MessageOccurrenceSpecification) fragment
                    .getFinish();
            behavior.setFinish(messageReader(specification.getMessage()));
        }
        return behavior;
    }

    /**
     * gate reader read sequenceGate from sequenceDiagram
     *
     * @param gate Gate
     * @return SequenceGate
     */
    private SequenceGate gateReader(Gate gate) {
        SequenceGate sequenceGate = new SequenceGate();
        sequenceGate.setGateMessage(gate.getMessage().getName());
        MessageEnd interactionFragment = gate.getMessage().getReceiveEvent();

        if (interactionFragment instanceof MessageOccurrenceSpecificationImpl) {
            MessageOccurrenceSpecificationImpl fragment = (MessageOccurrenceSpecificationImpl) interactionFragment;
            for (Lifeline lifeline : fragment.getCovereds()) {
                SequenceLifeline sequenceLifeline = new SequenceLifeline();
                sequenceLifeline.setLifelineName(lifeline.getName());
                sequenceLifeline.setRepresents(lifeline.getRepresents()
                        .getName());
                sequenceGate.setGateLifeline(sequenceLifeline);
            }
        }
        return sequenceGate;
    }

    /**
     * lifelineReader read lifeline name representation from Lifeline
     *
     * @param lifeline Lifeline
     * @return SequenceLifeline
     */
    private SequenceLifeline lifelineReader(Lifeline lifeline) {
        SequenceLifeline sequenceLifeline = new SequenceLifeline();
        sequenceLifeline.setLifelineName(lifeline.getName());
        sequenceLifeline.setRepresents(lifeline.getRepresents().getName());
        return sequenceLifeline;
    }

    /**
     * messageReader read sequence message and get message Name, Type, Sender
     * and Receiver lifeLines
     *
     * @param message Message
     * @return SequenceMessage
     */
    private SequenceMessage messageReader(Message message) {
        SequenceMessage sequenceMessage = new SequenceMessage();
        if (message != null) {
            if (!message.getName().isEmpty()) {
                if (!message.getName().contains("(")
                        && !message.getName().contains(")")) {
                    if (!message.getName().contains(" ")) {
                        sequenceMessage.setMessageName(message.getName());
                        sequenceMessage.setMessageType(message.getMessageSort()
                                .toString());
                        // Send Event
                        if (message.getSendEvent() instanceof MessageOccurrenceSpecification) {
                            MessageOccurrenceSpecification specification = (MessageOccurrenceSpecification) message
                                    .getSendEvent();
                            for (Lifeline lifeline : specification
                                    .getCovereds()) {
                                SequenceLifeline sequenceLifeline = new SequenceLifeline();
                                sequenceLifeline.setLifelineName(lifeline
                                        .getName());
                                sequenceLifeline.setRepresents(lifeline
                                        .getRepresents().getName());
                                sequenceMessage.setSender(sequenceLifeline);
                            }
                        }

                        // Received Event
                        if (message.getReceiveEvent() instanceof MessageOccurrenceSpecification) {
                            MessageOccurrenceSpecification specification = (MessageOccurrenceSpecification) message
                                    .getReceiveEvent();
                            for (Lifeline lifeline : specification
                                    .getCovereds()) {
                                SequenceLifeline sequenceLifeline = new SequenceLifeline();
                                sequenceLifeline.setLifelineName(lifeline
                                        .getName());
                                sequenceLifeline.setRepresents(lifeline
                                        .getRepresents().getName());
                                sequenceMessage.setReciver(sequenceLifeline);

                            }
                        }

                    }
                }
            }
        }
        return sequenceMessage;
    }

    private ArrayList<ClassOperation> readOperations(EList<Operation> eList) {
        ArrayList<String> opps = new ArrayList<>();
        ArrayList<ClassOperation> structure = new ArrayList<>();
        if (!eList.isEmpty()) {
            for (Operation oper : eList) {

                ClassOperation operation = new ClassOperation();
                operation.setName(oper.getName());
                operation.setVisibility(oper.getVisibility()
                        .toString());

                EList<Parameter> parameters = oper.getOwnedParameters();

                if (!parameters.isEmpty()) {
                    for (Parameter parameter : parameters) {
                        OperationParameter operationParameter = new OperationParameter();

						/*
                         * System.out.println(parameter.getName()+"  "+(
						 * parameter.getDirection() == null?"Return":"void"
						 * )+" "+parameter.getDirection());
						 */

                        operationParameter.setDirection(parameter.getDirection().toString());
                        if (parameter.getType() instanceof PrimitiveTypeImpl) {
                            operationParameter.setName(parameter.getName());
                            PrimitiveTypeImpl prim = (PrimitiveTypeImpl) (parameter
                                    .getType());
                            operationParameter.setType(prim.eProxyURI().fragment());

                        } else if (parameter.getType() instanceof org.eclipse.uml2.uml.internal.impl.InterfaceImpl) {

                            operationParameter.setName(parameter.getName());
                            operationParameter.setType("List<E>");
                        }
                        if (parameter.getDirection() == null) {
                            operation.setReturnType(new OperationReturn(operationParameter.getType()));
                        } else {
                            operation.setReturnType(new OperationReturn("void"));
                        }
                        operation.getParameters().add(operationParameter);
                    }
                }

                if (!opps.contains(operation.getName())) {
                    opps.add(operation.getName());
                    structure.add(operation);
                }
            }
        }
        return structure;
    }

    private ArrayList<ClassAttribute> readAttributes(EList<Property> attributes) {
        ArrayList<ClassAttribute> structure = new ArrayList<ClassAttribute>();
        if (!attributes.isEmpty()) {
            for (Property attribute : attributes) {
                ClassAttribute classAttribute = new ClassAttribute();
                if (attribute.getType() instanceof PrimitiveTypeImpl) {
                    classAttribute.setName(attribute.getName());
                    classAttribute.setVisibility(attribute.getVisibility()
                            .toString());
                    PrimitiveTypeImpl prim = (PrimitiveTypeImpl) (attribute
                            .getType());
                    classAttribute.setType(prim.eProxyURI().fragment());
                    structure.add(classAttribute);
                } else if (attribute.getType() instanceof org.eclipse.uml2.uml.internal.impl.InterfaceImpl) {
                    /*
                     * InterfaceImpl prim = (InterfaceImpl) (attribute
					 * .getType()); classAttribute.setType(prim.eProxyURI().query());
					 * structure.getAttributes().add(classAttribute);
					 */
                }

            }
        }
        return structure;
    }
}
