package com.skq.zookeeper.version2.server;

/**
 * Created by Administrator on 2019/7/21 0021.
 */
public interface BalanceUpdateProvider {

    // 增加负载
    public boolean addBalance(Integer step);

    // 减少负载
    public boolean reduceBalance(Integer step);

}
