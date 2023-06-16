package client;

import common.ReadWrite;
import common.Message;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Connector <T>{
    private ReadWrite<Message> readWrite;
    private final String ip;
    private final int port;
    private File file;

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
            Message message = new Message(clientName, messageText);
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
    public static String readWithScanner(String filename) throws IOException {
        String fileText = null;
        try (Scanner scanner = new Scanner(new File(filename), StandardCharsets.UTF_8)) {
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
            if (scanner.ioException() != null) {
                throw scanner.ioException();
            }
            fileText = sb.toString();
        }
        return fileText;
    }

    public void sendFile(String clientName, String file) {
        try {
            Message message = new Message(clientName, file);
            readWrite.sendMessage(message);
        } catch (IOException e) {
            System.out.println("Файл не может быть отправлен");
        }
    }


    @Override
    public String toString() {
        return "Connector{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}