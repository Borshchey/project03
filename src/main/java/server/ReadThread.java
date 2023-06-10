package server;

import common.ReadWrite;
import common.Message;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

public class ReadThread extends Thread{

    private ArrayBlockingQueue<MessageFromClient> messages;
    private ReadWrite<Message> readWrite;

    public ReadThread (ArrayBlockingQueue<MessageFromClient> messages, ReadWrite<Message> readWrite){
        this.messages = messages;
        this.readWrite = readWrite;
    }

    @Override
    public void run(){

        while (!Thread.currentThread().isInterrupted()){

            try {
                Message clientMessage = readWrite.readMessage();
                MessageFromClient fromClient = new MessageFromClient(clientMessage, readWrite);
                messages.put(fromClient);
                System.out.println(fromClient.getMessage().getText());
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}