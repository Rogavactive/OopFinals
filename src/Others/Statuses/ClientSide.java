package Others.Statuses;


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSide {


    private static class ServerListener extends Thread {

        private Socket socket;

        public ServerListener(Socket s) {
            this.socket = s;
        }

        public void run() {
            try (InputStream is = socket.getInputStream()) {
                InputStreamReader isReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isReader);
                while (!isInterrupted()) {
                    String line = reader.readLine();
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String [] args) throws IOException {
        Socket s = null;
        try {
            s = new Socket("localhost", 9080);
            ServerListener listener = new ServerListener(s);
            listener.start();

            OutputStream os = s.getOutputStream();
            PrintWriter out = new PrintWriter(os, true);

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }
                out.println(line);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(s!= null){
                s.close();
            }
        }
    }

}
