package Others.Statuses;

import javafx.concurrent.Worker;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerSide extends Thread{

    private ConcurrentHashMap<String, String> statuses;
    private BlockingQueue<Socket> connectedClients;

    public class Worker extends Thread{

        private Socket socket;

        Worker(Socket s){
            socket = s;
        }

        @Override
        public void run(){
            try (InputStream is = socket.getInputStream()) {
                InputStreamReader isReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isReader);
                System.out.println("New client: " + socket.getInetAddress().toString());

                while (!isInterrupted()) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }

                    int idx = line.indexOf(':');
                    if(idx==-1){
                        String response = statuses.get(line);
                        if(response==null){
                            response = "User not found";
                        }
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println(response);
                        System.out.println("get status " + response);
                    }else{
                        String username = line.substring(0,idx);
                        String statusMsg = line.substring(idx+1);
                        System.out.println("new status " + username + " - " + statusMsg);
                        statuses.put(username,statusMsg);
                    }

//                    System.out.println(socket.getInetAddress().toString() + "> " + line);
//                    for (Socket s : connectedClients) {
//                        if (s != socket && !s.isClosed()) {
//                            System.out.println("Sent to: " + s.getInetAddress().toString());
//                            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
//                            out.println(line);
//                        }
//                    }
                }
            } catch (SocketException e){
                e.printStackTrace();
            } catch (IOException e ) {
                e.printStackTrace();
            } finally {
                System.out.println("End of client: " + socket.getInetAddress().toString());
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void run(){
        statuses = new ConcurrentHashMap<>();
        connectedClients = new LinkedBlockingQueue<>();
        try {
            ServerSocket ss = new ServerSocket(9080);
            while (true) {
                Socket s = ss.accept();
                connectedClients.add(s);

                new Worker(s).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            for(Socket s:connectedClients){
                if(s!=null){
                    try {
                        s.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        ServerSide server = new ServerSide();
        server.start();

        try {
            server.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
