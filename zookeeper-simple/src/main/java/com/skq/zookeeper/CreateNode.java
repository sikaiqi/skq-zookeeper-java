package com.skq.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;

import java.util.HashMap;
import java.util.Map;

//创建节点
public class CreateNode {


    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1", 2000, 2000, new SerializableSerializer());
        Map<String,String> map = new HashMap<>();
        map.put("aa","1111");
        String path = zkClient.create("/uuu", map, CreateMode.PERSISTENT);
        System.out.println("create path:"+path);
    }
}
