package com.cn.jmw.enums;

import org.redisson.connection.AddressResolverGroupFactory;
import org.redisson.connection.DnsAddressResolverGroupFactory;
import org.redisson.connection.RoundRobinDnsAddressResolverGroupFactory;

/**
 * @Author jmw
 * @Description
 * @Date 14:33 2022/9/6
 **/
public enum AddressResolverGroupFactoryType {

    /**
     * 默认
     */
    DEFAULT {
        @Override
        public AddressResolverGroupFactory getInstance() {
            return new DnsAddressResolverGroupFactory();
        }
    },

    /**
     * 轮询
     */
    ROUND_ROBIN {
        @Override
        public AddressResolverGroupFactory getInstance() {
            return new RoundRobinDnsAddressResolverGroupFactory();
        }
    };

    public abstract AddressResolverGroupFactory getInstance();

}