package client;

import java.util.Scanner;

public class SendTread extends Thread{
    private Scanner scanner;
    private Connector connector;
    private String clientName;
    public SendTread(Scanner scanner, Connector connector,String clientName){
        this.connector=connector;
        this.scanner=scanner;
        this.clientName=clientName;
    }
    @Override
    public void run(){
        String text;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Введите сообщение");
            text = scanner.nextLine();
            if (text.equalsIgnoreCase("/exit")) Thread.currentThread().interrupt();
            connector.sendNewMessage(clientName, text);
            System.out.println("Сообщение отправлено");
        }
    }
}