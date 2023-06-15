import static java.lang.Thread.sleep;

public class ClientMain {
    public static void main(String[] args) throws InterruptedException {
        Client client = new Client("localhost", 5000);
    }
}