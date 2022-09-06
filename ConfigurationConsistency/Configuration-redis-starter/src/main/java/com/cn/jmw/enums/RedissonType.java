package com.cn.jmw.enums;

/**
 * @Author jmw
 * @Description
 * @Date 14:34 2022/9/6
 */
public enum RedissonType {

    /**
     * 单节点模式
     */
    SINGLE,

    /**
     * 集群模式
     */
    CLUSTER,

    /**
     * 主从模式
     */
    MASTER_SLAVE,

    /**
     * 哨兵模式
     */
    SENTINEL,

    /**
     * 云托管模式
     */
    REPLICATED

}