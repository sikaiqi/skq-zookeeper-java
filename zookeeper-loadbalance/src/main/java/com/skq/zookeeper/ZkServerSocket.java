package com.skq.zookeeper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * zookeeper 负载均衡实现
 * zookeeper 服务端
 */
public class ZkServerSocket implements Runnable{


    private int port;

    public ZkServerSocket(int port) {
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
        ZkServerSocket server = new ZkServerSocket(port);
        Thread thread = new Thread(server);
        thread.start();
    }



}
