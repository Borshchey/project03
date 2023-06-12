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
    private CopyOnWriteArrayList<ReadWrite> connections;
    private ArrayBlockingQueue<MessageFromClient> messages = new ArrayBlockingQueue(10, true);


    public Server() {
        this.port = port;
        connections = new CopyOnWriteArrayList<>();
    }
    public void startServerApp() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Сервер запущен " + serverSocket);
            new SendingStream(messages, connections).start();
            while (true) {
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                    System.out.println("Установлено соединение с клиентом");
                    ReadWrite<Message> readWrite = new ReadWrite<>(socket);
                    connections.add(readWrite);
                    new ReadThread(messages, readWrite).start();
                } catch (IOException e) {
                    System.out.println("Не удалось установить соединение с клиентом");
                    e.printStackTrace();
                    if (socket != null) socket.close();
                }
            }
        } catch (IOException e) {
            System.out.println("Порт для установки соединения занят или порт указан неверно");
            e.printStackTrace();
        }
    }
}

