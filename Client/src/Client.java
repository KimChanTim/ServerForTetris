import java.io.*;
import java.net.Socket;
import java.util.*;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private ObjectInputStream inObj;

    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);

            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            inObj = new ObjectInputStream(socket.getInputStream());

            sendMessage("Player 21");
            Boolean isRecord = (Boolean) recvObject();
            System.out.println(isRecord);
            sendMessage("U");
            ArrayList<String> strings = (ArrayList<String>) recvObject();
            ArrayList<Integer> integers = (ArrayList<Integer>) recvObject();

            System.out.println(strings);
            System.out.println(integers);
            sendMessage("Player 31");
            isRecord = (Boolean) recvObject();
            System.out.println(isRecord);
            sendMessage("Player 11");
            isRecord = (Boolean) recvObject();
            System.out.println(isRecord);
            sendMessage("U");
            strings = (ArrayList<String>) recvObject();
            integers = (ArrayList<Integer>) recvObject();

            System.out.println(strings);
            System.out.println(integers);
            closeSession();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeSession() {
        try {
            sendMessage("stop");

            socket.close();
            out.close();
            inObj.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage(String str) {
        out.println(str);
    }

    public Object recvObject() {
        try {
            return inObj.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}