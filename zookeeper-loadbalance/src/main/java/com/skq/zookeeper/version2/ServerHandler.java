package com.skq.zookeeper.version2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            pw = new PrintWriter(this.socket.getOutputStream());

            String line;
            while((line = br.readLine())!= null){
                System.out.println("Receive : " + line);
                pw.println("Hello, " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != pw){
                try {
                    pw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (null != br){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != socket){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
