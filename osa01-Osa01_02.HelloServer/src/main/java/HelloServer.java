
import java.io.File;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class HelloServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);

        while (true) {
            
            Socket socket = server.accept();

            Scanner lukija = new Scanner(socket.getInputStream());
            String pyynto = lukija.nextLine();

            if (pyynto.equals("quit")) {

                break;

            }

            PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream());
            kirjoittaja.println("HTTP/1.1 302 Found");
            kirjoittaja.println();

            Path path = Paths.get("index.html");
            Scanner jonneilija = new Scanner(new File(path.toUri()));

            while (jonneilija.hasNext()) {
                kirjoittaja.println(jonneilija.nextLine());
            }

            kirjoittaja.flush();

            lukija.close();
            kirjoittaja.close();
            socket.close();
            jonneilija.close();
        }
        
    }
}
