package org.eclipse.emf.example.models._sequence;

public class SequenceMessage {
    private String messageType;
    private String MessageName;
    private SequenceLifeline Sender;
    private SequenceLifeline Reciver;

    public SequenceMessage(String messageType, String messageName,
                           SequenceLifeline sender, SequenceLifeline reciver) {
        super();
        this.messageType = messageType;
        MessageName = messageName;
        Sender = sender;
        Reciver = reciver;
    }

    public SequenceMessage() {
        super();
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageName() {
        return MessageName;
    }

    public void setMessageName(String messageName) {
        MessageName = messageName;
    }

    public SequenceLifeline getSender() {
        return Sender;
    }

    public void setSender(SequenceLifeline sender) {
        Sender = sender;
    }

    public SequenceLifeline getReciver() {
        return Reciver;
    }

    public void setReciver(SequenceLifeline reciver) {
        Reciver = reciver;
    }

}
