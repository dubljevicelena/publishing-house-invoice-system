/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import threads.HandleClientThread;

/**
 *
 * @author Dell
 */
public class Server extends Thread {
    boolean kraj = false;
    ServerSocket serverSocket;
    
    List<HandleClientThread> klijenti;

    public Server() {
        klijenti = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);
            while(!kraj)
            {
                Socket s = serverSocket.accept();
                System.out.println("Klijent je povezan");
                HandleClientThread ct= new HandleClientThread(s);
                klijenti.add(ct);
                ct.start();
            }
        } catch (IOException ex) {
            System.out.println("Server je ugasen!");
        }
    }
    
    public void zaustaviServer() throws IOException
    {
        kraj = true;
        try {
            serverSocket.close();
            for(HandleClientThread ct : klijenti)
            {
                ct.prekini();
            }
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
        
    }
    
}
