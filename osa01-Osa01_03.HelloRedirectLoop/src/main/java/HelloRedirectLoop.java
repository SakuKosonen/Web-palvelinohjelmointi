
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HelloRedirectLoop {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        int mones = 0;
        while (true) {
            Socket socket = server.accept();

            Scanner lukija = new Scanner(socket.getInputStream());
            String pyynto = lukija.nextLine();

            if (pyynto.equals("quit")) {
                
                break;

            } else {

                PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream());
                kirjoittaja.println("HTTP/1.1 302 Found");
                kirjoittaja.println("Location: http://localhost:8080");
                kirjoittaja.println();
                kirjoittaja.flush();

                lukija.close();
                kirjoittaja.close();
                socket.close();
                mones++;
                System.out.println(mones);
            }
        }
    }
}
