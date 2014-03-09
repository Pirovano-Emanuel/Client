import java.net.*;
import java.io.*;
 
class Client {      // Classe client
 
    public static void main(String args[]) throws IOException {     // Main che gestisce le eccezioni con il throws
        Socket sk = null;
        String sr = null;
        BufferedReader br = null;
        DataOutputStream data = null;
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));     // Input dal buffer
        try {
            sk = new Socket(InetAddress.getLocalHost(), 95);        // Finchè utilizziamo .getLocalHost() , lavoriamo in locale
            br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
            data = new DataOutputStream(sk.getOutputStream());
            } catch (UnknownHostException uhe) {
                System.out.println("Host sconosciuto");     // Stampa dell eccezione
                System.exit(0);
        }
        
        System.out.println("Inserire il messaggio da inoltrare   \nPremi Fine per uscire");     
        boolean x = true;
        while (x) {     // Ciclo while
            sr = buff.readLine();
            data.writeBytes(sr);
            data.write(13);
            data.write(10);
            data.flush();        // Flush !
            String s;
            String s1;
            s = br.readLine();
            System.out.println("Messaggio server : " + s);
            if (s.equals("Fine")) {     // Se l' utente inserisce la stringa "Fine"
                break;                  // Esce
            }
        }
        br.close();
        data.close();
        sk.close();
    }
}