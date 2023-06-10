package common;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    private String name;
    private String text;
    private LocalDateTime dateTime;


    public Message(String name, String text) {
        this.name = name;
        this.text = text;
        dateTime = LocalDateTime.now();
    }

    public String getText() {
        return "["+dateTime.toString()+"]"+name+": "+text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateTime() {
        dateTime = LocalDateTime.now();
    }


    @Override
    public String toString() {
        return "SimpleMessage{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}