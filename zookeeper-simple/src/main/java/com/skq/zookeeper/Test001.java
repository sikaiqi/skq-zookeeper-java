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
<<<<<<< HEAD
 * 主要是KeeperState.SyncConnected 中几种事件类型：
 * EventType.NodeCreated : 节点创建事件类型
 * EventType.NodeDeleted : 节点被删除
 * EventType.NodeDataChanged : 节点被修改
 * EventType.None : 客户端与服务器成功建立会话
 * EventType.NodeChildrenChanged : 子节点列表发生变更
 */
public class Test001 {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SESSION_TIMEOUT = 2000;
    //信号量,阻塞程序执行,用户等待zookeeper连接成功,发送成功信号，
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = new ZooKeeper(SERVER_ADDRESS, SESSION_TIMEOUT, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                //获取事件状态
                KeeperState state = watchedEvent.getState();
                //获取事件类型
                EventType type = watchedEvent.getType();
                if (KeeperState.SyncConnected.equals(state)) {
                    if (EventType.None.equals(type)) {
                        COUNT_DOWN_LATCH.countDown();
                        System.out.println("zk server 连接启动成功！");
                    }
                }

            }
        });
        System.out.println("zk server  等待连接。。。。");
        //zk 未连接上服务时需要阻塞。。。
        COUNT_DOWN_LATCH.await();

        String node = zk.create("/skq", "first zk node".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(node);
        zk.close();
    }
}
