package client;

import java.io.IOException;
import java.util.Scanner;

public class SendTread extends Thread{
    private Scanner scanner;
    private Connector connector;
    private String clientName;
    public SendTread(Scanner scanner, Connector connector,String clientName){
        this.connector = connector;
        this.scanner = scanner;
        this.clientName = clientName;
    }
    @Override
    public void run(){
        String text;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Введите сообщение");
            text = scanner.nextLine();
            if (text.equalsIgnoreCase("/exit")) {
                Thread.currentThread().interrupt();
            }

            if (text.equals("/sendFile")) {
                System.out.println("Загрузите файл");
                try {
                    connector.readWithScanner("example.txt");
                    connector.sendFile();
                } catch (IOException e) {
                    System.out.println("Не удалось прочитать файл");
                    e.printStackTrace();
                }
                System.out.println("Файл отправлен");

            }

            if (text.equals("/readFile")) {
                System.out.println("Введите имя файла из списка:");
                connector.readFile();
            }

            connector.sendNewMessage(clientName, text);
            System.out.println("Сообщение отправлено");
        }
    }
}