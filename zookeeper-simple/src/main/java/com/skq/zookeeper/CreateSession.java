package com.skq.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

public class CreateSession {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181",2000,2000,new SerializableSerializer());
        System.out.println("created ok");
    }
}
