package server;

import common.ReadWrite;
import common.Message;

public class MessageFromClient {
    private Message message;
    private ReadWrite readWrite;

    public MessageFromClient(Message message, ReadWrite readWrite) {
        this.message = message;
        this.readWrite = readWrite;
    }

    public Message getMessage() {
        return message;
    }

    public ReadWrite getConnection() {
        return readWrite;
    }
}