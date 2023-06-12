package client;

import java.util.Scanner;

public class Client {
    private final String ip;
    private final int port = 1111;
    private final String clientName;
    private Connector connector;

    public Client() {
        String [] clientNames = new  String[] {"Первый", "Второй", "Третий"};
        clientName = clientNames[(int)(Math.random() * 3)];
        this.ip = "0.0.0.0";
    }

    public void startClientApp(){
        connector = new Connector(ip, port);
        System.out.println(connector);
        Scanner scanner = new Scanner(System.in);
        SendTread sendTread = new SendTread(scanner, connector, clientName);
        ReceiveTread receiveTread = new ReceiveTread(connector);
        sendTread.start();
        receiveTread.start();
        try {
            sendTread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
