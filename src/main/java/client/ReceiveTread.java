package client;

public class ReceiveTread extends Thread{
    private final Connector connector;
    public ReceiveTread(Connector connector){
        this.connector=connector;
    }
    @Override
    public void run(){
        while (true){
            System.out.println(connector.receiveMessage().getText());
        }
    }
}