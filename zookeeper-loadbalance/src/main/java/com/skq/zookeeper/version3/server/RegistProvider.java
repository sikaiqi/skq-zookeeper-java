package com.skq.zookeeper.version3.server;

/**
 * Created by Administrator on 2019/7/21 0021.
 */
public interface RegistProvider {

    public void regist(Object context) throws Exception;

    public void unRegist(Object context) throws Exception;

}