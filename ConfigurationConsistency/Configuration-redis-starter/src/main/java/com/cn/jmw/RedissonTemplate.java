package com.cn.jmw;//package com.eoi.fly.redis;
//
//import com.eoi.fly.redis.enums.BusinessCodeEnum;
//import com.eoi.fly.utils.StringUtils;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
//import javax.annotation.Resource;
//import java.util.concurrent.TimeUnit;
//
//public class RedissonTemplate {
//
//    @Resource
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Resource
//    private RedissonClient redissonClient;
//
//    /**
//     * Business Code lock key
//     */
//    private static final String DIS_KEY = "LOCK";
//
//    private static final String BEGIN_NUMBER = "1000";
//
//    private static final long INCREMENT_NUMBER = 1;
//
//    public int getBusinessCode(String orgCode, BusinessCodeEnum businessCodeEnum, int businessCode) {
//
//        if (StringUtils.isEmpty(orgCode)) {
//            return 0;
//        }
//        String key = businessCodeEnum.getKey(orgCode);
//
//        String code = stringRedisTemplate.opsForValue().get(key);
//        if (StringUtils.isNotEmpty(code)) {
//            return stringRedisTemplate.opsForValue().increment(key, INCREMENT_NUMBER).intValue();
//        } else {
//            final RLock rLock = redissonClient.getLock(DIS_KEY);
//            try {
//                rLock.lock();
//                /**
//                 * 双重校验 防止加分布式锁之前有线程获取锁并且执行完成
//                 */
//                code = stringRedisTemplate.opsForValue().get(key);
//                if (StringUtils.isNotEmpty(code)) {
//                    return stringRedisTemplate.opsForValue().increment(key, INCREMENT_NUMBER).intValue();
//                }
//                if (businessCode == 0) {
//                    //起始number
//                    stringRedisTemplate.opsForValue().set(key, BEGIN_NUMBER);
//                    //increment执行原子性 +1
//                    stringRedisTemplate.opsForValue().increment(key, INCREMENT_NUMBER).intValue();
//                } else {
//                    setBusinessCode(orgCode, ++businessCode, businessCodeEnum);
//                }
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            } finally {
//                rLock.unlock();
//            }
//            return Integer.parseInt(stringRedisTemplate.opsForValue().get(key));
//        }
//    }
//
//    public void setBusinessCode(String orgCode, Integer code, BusinessCodeEnum businessCodeEnum) {
//
//        String key = businessCodeEnum.getKey(orgCode);
//
//        stringRedisTemplate.opsForValue().set(key, String.valueOf(code));
//    }
//
//    /**
//     * 获取锁
//     *
//     * @param lockKey
//     */
//    public void lock(String lockKey) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.lock();
//    }
//
//    /**
//     * 释放锁
//     *
//     * @param lockKey
//     */
//    public void unlock(String lockKey) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.unlock();
//    }
//
//    /**
//     * 获取锁，超时释放
//     *
//     * @param lockKey
//     * @param leaseTime
//     */
//    public void lock(String lockKey, int leaseTime) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.lock(leaseTime, TimeUnit.SECONDS);
//    }
//
//    /**
//     * 获取锁，超时释放，指定时间单位
//     *
//     * @param lockKey
//     * @param unit
//     * @param timeout
//     */
//    public void lock(String lockKey, TimeUnit unit, int timeout) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.lock(timeout, unit);
//    }
//
//    /**
//     * 尝试获取锁，获取到立即返回true,获取失败立即返回false
//     *
//     * @param lockKey
//     * @return
//     */
//    public boolean tryLock(String lockKey) {
//        RLock lock = redissonClient.getLock(lockKey);
//        return lock.tryLock();
//    }
//
//    /**
//     * 尝试获取锁，在给定的waitTime时间内尝试，获取到返回true,获取失败返回false,获取到后再给定的leaseTime时间超时释放
//     *
//     * @param lockKey
//     * @param waitTime
//     * @param leaseTime
//     * @param unit
//     * @return
//     * @throws InterruptedException
//     */
//    public boolean tryLock(String lockKey, long waitTime, long leaseTime,
//                           TimeUnit unit) throws InterruptedException {
//        RLock lock = redissonClient.getLock(lockKey);
//        return lock.tryLock(waitTime, leaseTime, unit);
//    }
//
//    /**
//     * 锁是否被任意一个线程锁持有
//     *
//     * @param lockKey
//     * @return
//     */
//    public boolean isLocked(String lockKey) {
//        RLock lock = redissonClient.getLock(lockKey);
//        return lock.isLocked();
//    }
//
//}
