package client;

import common.Connection;
import common.Message;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.Socket;

public class Connector {
    private Connection<Message> connection;
    private final String ip;
    private final int port;
    public Connector(String ip, int port){
        this.ip=ip;
        this.port=port;
        try {
            connection= new Connection<>(new Socket(ip, port));

        } catch (IIOException  e){
            System.out.println("Не удается соединится с сервером");
        }catch (Exception e){
            System.out.println("Сервер недоступен");
            throw new RuntimeException("Сервер не найден");
        }
    }
    public void sendNewMessage(String clientName, String messageText){
        try {
            Message message = new Message(clientName,messageText);
            connection.sendMessage(message);
        } catch (IOException e) {
            System.out.println("Сообщение не может быть отправлено");
        }
    }
    public Message receiveMessage(){
        try {
            return connection.readMessage();
        } catch (IIOException | ClassNotFoundException e) {
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