package common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ReadWrite<T extends Message> implements AutoCloseable{

    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public ReadWrite(Socket socket) throws IOException {
        this.socket = socket;
        output = new ObjectOutputStream(this.socket.getOutputStream());
        input = new ObjectInputStream(this.socket.getInputStream());
    }

    public void sendMessage (T message)throws IOException{
        message.setDateTime();
        output.writeObject(message);
        output.flush();

    }

    public T readMessage()throws IOException, ClassNotFoundException{
        return (T) input.readObject();

    }

    @Override
    public void close() throws Exception {
        input.close();
        output.close();
        socket.close();
    }
}