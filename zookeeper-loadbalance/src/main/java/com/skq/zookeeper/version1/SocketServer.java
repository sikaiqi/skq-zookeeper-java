package com.skq.zookeeper.version1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketServer implements Runnable{


    private int port;

    public SocketServer(int port) {
        this.port = port;
    }

    public void run() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(port);
            System.out.println("server start port:"+port);
            Socket socket = null;
            while(true){
                socket = ss.accept();
                new Thread(new ServerHandler(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != ss){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        int port = 18080;
        SocketServer server = new SocketServer(port);
        Thread thread = new Thread(server);
        thread.start();
    }



}
