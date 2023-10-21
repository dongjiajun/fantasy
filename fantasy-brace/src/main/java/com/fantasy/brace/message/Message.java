package com.fantasy.brace.message;

/**
 * 消息类
 *
 * @author DongJiaJun
 */
public class Message {
    private String message;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
