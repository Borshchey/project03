package server;

import common.ReadWrite;
import common.Message;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private int port = 1111;
    private CopyOnWriteArrayList<ReadWrite> connectionsCollect = new CopyOnWriteArrayList();
    private ArrayBlockingQueue<MessageFromClient> messages = new ArrayBlockingQueue(10, true);


    public Server() {
        this.port = port;
    }
    public void startServerApp() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Сервер запущен " + serverSocket);
            new SendingStream(messages, connectionsCollect).start();
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Установлено соединение с клиентом");
                ReadWrite<Message> readWrite = new ReadWrite<>(socket);
                connectionsCollect.add(readWrite);
                new ReadThread(messages, readWrite).start();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

