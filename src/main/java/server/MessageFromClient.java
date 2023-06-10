package server;

import common.Connection;
import common.Message;

public class MessageFromClient {
    private Message message;
    private Connection connection;

    public MessageFromClient(Message message, Connection connection) {
        this.message = message;
        this.connection = connection;
    }

    public Message getMessage() {
        return message;
    }

    public Connection getConnection() {
        return connection;
    }
}