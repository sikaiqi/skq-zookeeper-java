package com.skq.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 主要是KeeperState.SyncConnected 中几种事件类型：
 * EventType.NodeCreated : 节点创建事件类型
 * EventType.NodeDeleted : 节点被删除
 * EventType.NodeDataChanged : 节点被修改
 * EventType.None : 客户端与服务器成功建立会话
 * EventType.NodeChildrenChanged : 子节点列表发生变更
 */
public class Test001 {
    //连接地址
    private static final String ADDRESS = "127.0.0.1";
    //连接超时时间
    private static final int SESSION_TIMEOUT = 2000;

    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = new ZooKeeper(ADDRESS, SESSION_TIMEOUT, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                //获取事件状态
                KeeperState state = watchedEvent.getState();
                //获取事件类型
                EventType type = watchedEvent.getType();
                if (KeeperState.SyncConnected == state) {
                    if (EventType.None == type) {
                        COUNT_DOWN_LATCH.countDown();
                        System.out.println("zk 启动连接。。。。");
                    }
                }
            }
        });
        // 进行阻塞
//        COUNT_DOWN_LATCH.await();
        String result = zk.create("/sldkdk", "11111".getBytes(), Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);
        System.out.println(result);
        zk.close();
    }
}

