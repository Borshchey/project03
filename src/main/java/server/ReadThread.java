package server;

import common.Connection;
import common.Message;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

public class ReadThread extends Thread{

    private ArrayBlockingQueue<MessageFromClient> messages;
    private Connection <Message> connection;

    public ReadThread (ArrayBlockingQueue<MessageFromClient> messages, Connection <Message> connection){
        this.messages = messages;
        this.connection = connection;
    }

    @Override
    public void run(){

        while (!Thread.currentThread().isInterrupted()){

            try {
                Message clientMessage = connection.readMessage();
                MessageFromClient fromClient = new MessageFromClient(clientMessage, connection);
                messages.put(fromClient);
                System.out.println(fromClient.getMessage().getText());
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}