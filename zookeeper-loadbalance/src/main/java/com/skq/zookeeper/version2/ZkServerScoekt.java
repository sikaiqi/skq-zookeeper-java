package com.skq.zookeeper.version2;

import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ZkServerScoekt implements Runnable {
    private static int port = 18081;

    public static void main(String[] args) throws IOException {
        ZkServerScoekt server = new ZkServerScoekt(port);
        Thread thread = new Thread(server);
        thread.start();
    }

    public ZkServerScoekt(int port) {
        this.port = port;
    }

    public void regServer() {
        // 向ZooKeeper注册当前服务器
        ZkClient client = new ZkClient("127.0.0.1:2181", 60000, 1000);
        String path = "/test/server" + port;
        if (client.exists(path))//检查节点是否存在
            client.delete(path);
        client.createEphemeral(path, "127.0.0.1:" + port);//创建临时节点
    }

    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            regServer();
            System.out.println("Server start port:" + port);
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (Exception e2) {

            }
        }
    }

}