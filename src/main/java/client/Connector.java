package client;

import common.ReadWrite;
import common.Message;

import java.io.IOException;
import java.net.Socket;

public class Connector {
    private ReadWrite<Message> readWrite;
    private final String ip;
    private final int port;
    public Connector(String ip, int port){
        this.ip = ip;
        this.port = port;
        try {
            readWrite = new ReadWrite<>(new Socket(ip, port));

        } catch (IOException  e){
            System.out.println("Не удается соединится с сервером");
        }catch (Exception e){
            System.out.println("Сервер недоступен");
            throw new RuntimeException("Сервер не найден");
        }
    }
    public void sendNewMessage(String clientName, String messageText){
        try {
            Message message = new Message(clientName,messageText);
            readWrite.sendMessage(message);
        } catch (IOException e) {
            System.out.println("Сообщение не может быть отправлено");
        }
    }
    public Message receiveMessage(){
        try {
            return readWrite.readMessage();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Не удается прочитать сообщение");
        } catch (Exception e) {
            System.out.println("Не удается прочитать сообщение");
        }
        return null;
    }

    @Override
    public String toString() {
        return "Connector{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}