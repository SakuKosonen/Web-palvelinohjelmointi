
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class HelloBrowser {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.println("================\n"
                + "THE INTERNETS!\n"
                + "================");

        System.out.print("Where to? ");
        String osoite = input.nextLine();
        System.out.println("");
        System.out.println("==========\n"
                + "RESPONSE\n"
                + "==========");

        int portti = 80;

        try {
            Socket socket = new Socket(osoite, portti);
            PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream());
            kirjoittaja.println("GET / HTTP/1.1");
            kirjoittaja.println("Host: " + osoite);
            kirjoittaja.println();
            kirjoittaja.flush();

            Scanner lukija = new Scanner(socket.getInputStream());
            while (lukija.hasNextLine()) {
                System.out.println(lukija.nextLine());
            }
            lukija.close();
            kirjoittaja.close();
            socket.close();
        } catch (UnknownHostException e) {
            
            throw e;
        }
        
    }
}
