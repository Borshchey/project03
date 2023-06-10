package server;

import common.Connection;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class SendingStream extends Thread{

    private ArrayBlockingQueue<MessageFromClient> messages;
    private CopyOnWriteArrayList<Connection> connectionsCollect;

    public SendingStream (ArrayBlockingQueue<MessageFromClient> messages, CopyOnWriteArrayList<Connection> connectionsCollect){
        this.messages = messages;
        this.connectionsCollect = connectionsCollect;
    }


    @Override
    public void run(){

        while (!Thread.currentThread().isInterrupted()){
            MessageFromClient message;
            try {
                message = messages.take();
                connectionsCollect.forEach((connection)->{
                    if (!connection.equals(message.getConnection())){
                        try {
                            connection.sendMessage(message.getMessage());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
