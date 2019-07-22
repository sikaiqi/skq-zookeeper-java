package com.skq.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.data.Stat;

import java.util.Map;

public class GetNode {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 2000, 2000, new SerializableSerializer());
        Stat stat = new Stat();
        Map map = zkClient.readData("/uuu",stat);
        System.out.println(map);
        System.out.println(stat);
    }
}
