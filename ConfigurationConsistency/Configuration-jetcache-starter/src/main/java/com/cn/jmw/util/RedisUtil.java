//package com.cn.jmw.cache.util;
//
//import com.cn.jmw.utils.StringUtils;
//import org.springframework.data.redis.connection.DataType;
//import org.springframework.data.redis.core.Cursor;
//import org.springframework.data.redis.core.ScanOptions;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.*;
//import java.util.Map.Entry;
//import java.util.concurrent.TimeUnit;
//
//
///**
// * @author zhuwukai
// * @date 2021.11.18
// */
//@Component
//public class RedisUtil {
//
//    @Resource
//    private StringRedisTemplate stringRedisTemplate;
//
//    // *********************************************** key相关操作 start
//
//    /**
//     * 删除key
//     */
//    public void delete(String key) {
//        stringRedisTemplate.delete(key);
//    }
//
//    /**
//     * 批量删除key
//     */
//    public void delete(Collection<String> keys) {
//        stringRedisTemplate.delete(keys);
//    }
//
//    /**
//     * 是否存在key
//     */
//    public Boolean hasKey(String key) {
//        return stringRedisTemplate.hasKey(key);
//    }
//
//    /**
//     * 设置过期时间
//     */
//    public Boolean expire(String key, long timeout, TimeUnit unit) {
//
//        return stringRedisTemplate.expire(key, timeout, unit);
//    }
//
//
//    /**
//     * 设置过期时间
//     */
//    public Boolean expireAt(String key, Date date) {
//        return stringRedisTemplate.expireAt(key, date);
//    }
//
//    /**
//     * 查找匹配的key
//     */
//    public Set<String> keys(String pattern) {
//        return stringRedisTemplate.keys(pattern);
//    }
//
//    /**
//     * 移除 key 的过期时间，key 将持久保持
//     */
//    public Boolean persist(String key) {
//        return stringRedisTemplate.persist(key);
//    }
//
//    /**
//     * 返回 key 的剩余的过期时间
//     */
//    public Long getExpire(String key, TimeUnit unit) {
//        return stringRedisTemplate.getExpire(key, unit);
//    }
//
//    /**
//     * 返回 key 的剩余的过期时间
//     */
//    public Long getExpire(String key) {
//        return stringRedisTemplate.getExpire(key);
//    }
//
//    /**
//     * 修改 key 的名称
//     */
//    public void rename(String oldKey, String newKey) {
//        stringRedisTemplate.rename(oldKey, newKey);
//    }
//
//    /**
//     * 仅当 newkey 不存在时，将 oldKey 改名为 newkey
//     */
//    public Boolean renameIfAbsent(String oldKey, String newKey) {
//        return stringRedisTemplate.renameIfAbsent(oldKey, newKey);
//    }
//
//    /**
//     * 返回 key 所储存的值的类型
//     */
//    public DataType type(String key) {
//        return stringRedisTemplate.type(key);
//    }
//
//    //*************************************************string相关操作
//
//    /**
//     * 设置指定 key 的值
//     */
//    public void set(String key, String value) {
//        stringRedisTemplate.opsForValue().set(key, value);
//    }
//
//    public void setExpired(String key,String value,Long timeout,TimeUnit unit){
//        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
//    }
//
//    /**
//     * 获取指定 key 的值
//     */
//    public String get(String key) {
//        return stringRedisTemplate.opsForValue().get(key);
//    }
//
//    /**
//     * 返回 key 中字符串值的子字符
//     */
//    public String getRange(String key, long start, long end) {
//        return stringRedisTemplate.opsForValue().get(key, start, end);
//    }
//
//    /**
//     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
//     */
//    public String getAndSet(String key, String value) {
//        return stringRedisTemplate.opsForValue().getAndSet(key, value);
//    }
//
//    /**
//     * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)
//     */
//    public Boolean getBit(String key, long offset) {
//        return stringRedisTemplate.opsForValue().getBit(key, offset);
//    }
//
//    /**
//     * 批量获取
//     */
//    public List<String> multiGet(Collection<String> keys) {
//        return stringRedisTemplate.opsForValue().multiGet(keys);
//    }
//
//    /**
//     * 只有在 key 不存在时设置 key 的值
//     */
//    public boolean setIfAbsent(String key, String value) {
//        return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
//    }
//
//    /**
//     * 获取字符串的长度
//     */
//    public Long size(String key) {
//        return stringRedisTemplate.opsForValue().size(key);
//    }
//
//    /**
//     * 批量添加
//     */
//    public void multiSet(Map<String, String> maps) {
//        stringRedisTemplate.opsForValue().multiSet(maps);
//    }
//
//
//    /**
//     * 增加(自增长), 负数则为自减
//     */
//    public Long incrBy(String key, long increment) {
//        return stringRedisTemplate.opsForValue().increment(key, increment);
//    }
//
//    /**
//     * 增加(自增长), 负数则为自减  浮点数
//     */
//    public Double incrByFloat(String key, double increment) {
//        return stringRedisTemplate.opsForValue().increment(key, increment);
//    }
//
//    /**
//     * 追加到末尾
//     */
//    public Integer append(String key, String value) {
//        return stringRedisTemplate.opsForValue().append(key, value);
//    }
//
//    //**************************************************hash相关操作
//
//    /**
//     * 获取存储在哈希表中指定字段的值
//     */
//    public Object hGet(String key, String field) {
//        return stringRedisTemplate.opsForHash().get(key, field);
//    }
//
//    /**
//     * 获取所有给定字段的值
//     */
//    public Map<Object, Object> hGetAll(String key) {
//        return stringRedisTemplate.opsForHash().entries(key);
//    }
//
//    /**
//     * 获取所有给定字段的值
//     */
//    public List<Object> hMultiGet(String key, Collection<Object> fields) {
//        return stringRedisTemplate.opsForHash().multiGet(key, fields);
//    }
//
//    public void hPut(String key, String hashKey, String value) {
//        stringRedisTemplate.opsForHash().put(key, hashKey, value);
//    }
//
//    public void hPutAll(String key, Map<String, String> maps) {
//        stringRedisTemplate.opsForHash().putAll(key, maps);
//    }
//
//    /**
//     * 仅当hashKey不存在时才设置
//     */
//    public Boolean hPutIfAbsent(String key, String hashKey, String value) {
//        return stringRedisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
//    }
//
//    /**
//     * 删除一个或多个哈希表字段
//     */
//    public Long hDelete(String key, Object... fields) {
//        return stringRedisTemplate.opsForHash().delete(key, fields);
//    }
//
//    /**
//     * 查看哈希表 key 中，指定的字段是否存在
//     */
//    public boolean hExists(String key, String field) {
//        return stringRedisTemplate.opsForHash().hasKey(key, field);
//    }
//
//    /**
//     * 为哈希表 key 中的指定字段的整数值加上增量 increment
//     */
//    public Long hIncrBy(String key, Object field, long increment) {
//        return stringRedisTemplate.opsForHash().increment(key, field, increment);
//    }
//
//    /**
//     * 为哈希表 key 中的指定字段的整数值加上增量 increment
//     */
//    public Double hIncrByFloat(String key, Object field, double delta) {
//        return stringRedisTemplate.opsForHash().increment(key, field, delta);
//    }
//
//    /**
//     * 获取所有哈希表中的字段
//     */
//    public Set<Object> hKeys(String key) {
//        return stringRedisTemplate.opsForHash().keys(key);
//    }
//
//    /**
//     * 获取哈希表中字段的数量
//     */
//    public Long hSize(String key) {
//        return stringRedisTemplate.opsForHash().size(key);
//    }
//
//    /**
//     * 获取哈希表中所有值
//     */
//    public List<Object> hValues(String key) {
//        return stringRedisTemplate.opsForHash().values(key);
//    }
//
//    /**
//     * 迭代哈希表中的键值对
//     */
//    public Cursor<Entry<Object, Object>> hScan(String key, ScanOptions options) {
//        return stringRedisTemplate.opsForHash().scan(key, options);
//    }
//
//    //**********************************************list相关操作
//
//    /**
//     * 通过索引获取列表中的元素
//     */
//    public String lIndex(String key, long index) {
//        return stringRedisTemplate.opsForList().index(key, index);
//    }
//
//    /**
//     * 获取列表指定范围内的元素
//     */
//    public List<String> lRange(String key, long start, long end) {
//        return stringRedisTemplate.opsForList().range(key, start, end);
//    }
//
//    /**
//     * 存储在list头部
//     */
//    public Long lLeftPush(String key, String value) {
//        return stringRedisTemplate.opsForList().leftPush(key, value);
//    }
//
//    /**
//     * 存储在list头部
//     */
//    public Long lLeftPushAll(String key, String... value) {
//        return stringRedisTemplate.opsForList().leftPushAll(key, value);
//    }
//
//    /**
//     * 存储在list头部
//     */
//    public Long lLeftPushAll(String key, Collection<String> value) {
//        return stringRedisTemplate.opsForList().leftPushAll(key, value);
//    }
//
//    /**
//     * 当list存在的时候才加入
//     */
//    public Long lLeftPushIfPresent(String key, String value) {
//        return stringRedisTemplate.opsForList().leftPushIfPresent(key, value);
//    }
//
//    /**
//     * 如果pivot存在,再pivot前面添加
//     */
//    public Long lLeftPush(String key, String pivot, String value) {
//        return stringRedisTemplate.opsForList().leftPush(key, pivot, value);
//    }
//
//    /**
//     * 存储在list尾部
//     */
//    public Long lRightPush(String key, String value) {
//        return stringRedisTemplate.opsForList().rightPush(key, value);
//    }
//
//    /**
//     * 存储在list尾部
//     */
//    public Long lRightPushAll(String key, String... value) {
//        return stringRedisTemplate.opsForList().rightPushAll(key, value);
//    }
//
//    /**
//     * 存储在list尾部
//     */
//    public Long lRightPushAll(String key, Collection<String> value) {
//        return stringRedisTemplate.opsForList().rightPushAll(key, value);
//    }
//
//    /**
//     * 为已存在的列表添加值
//     */
//    public Long lRightPushIfPresent(String key, String value) {
//        return stringRedisTemplate.opsForList().rightPushIfPresent(key, value);
//    }
//
//    /**
//     * 在pivot元素的右边添加值
//     */
//    public Long lRightPush(String key, String pivot, String value) {
//        return stringRedisTemplate.opsForList().rightPush(key, pivot, value);
//    }
//
//    /**
//     * 通过索引设置列表元素的值
//     */
//    public void lSet(String key, long index, String value) {
//        stringRedisTemplate.opsForList().set(key, index, value);
//    }
//
//    /**
//     * 移出并获取列表的第一个元素
//     */
//    public String lLeftPop(String key) {
//        return stringRedisTemplate.opsForList().leftPop(key);
//    }
//
//    /**
//     * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
//     */
//    public String lBLeftPop(String key, long timeout, TimeUnit unit) {
//        return stringRedisTemplate.opsForList().leftPop(key, timeout, unit);
//    }
//
//    /**
//     * 移除并获取列表最后一个元素
//     */
//    public String lRightPop(String key) {
//        return stringRedisTemplate.opsForList().rightPop(key);
//    }
//
//    /**
//     * 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
//     */
//    public String lBRightPop(String key, long timeout, TimeUnit unit) {
//        return stringRedisTemplate.opsForList().rightPop(key, timeout, unit);
//    }
//
//    /**
//     * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
//     */
//    public String lRightPopAndLeftPush(String sourceKey, String destinationKey) {
//        return stringRedisTemplate.opsForList().rightPopAndLeftPush(sourceKey,
//                destinationKey);
//    }
//
//    /**
//     * 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
//     */
//    public String lBRightPopAndLeftPush(String sourceKey, String destinationKey,
//                                        long timeout, TimeUnit unit) {
//        return stringRedisTemplate.opsForList().rightPopAndLeftPush(sourceKey,
//                destinationKey, timeout, unit);
//    }
//
//    /**
//     * 删除集合中值等于value得元素
//     */
//    public Long lRemove(String key, long index, String value) {
//        return stringRedisTemplate.opsForList().remove(key, index, value);
//    }
//
//    /**
//     * 裁剪list
//     */
//    public void lTrim(String key, long start, long end) {
//        stringRedisTemplate.opsForList().trim(key, start, end);
//    }
//
//    /**
//     * 获取列表长度
//     */
//    public Long lLen(String key) {
//        return stringRedisTemplate.opsForList().size(key);
//    }
//
//    /** --------------------set相关操作-------------------------- */
//
//    /**
//     * set添加元素
//     */
//    public Long sAdd(String key, String... values) {
//        return stringRedisTemplate.opsForSet().add(key, values);
//    }
//
//    /**
//     * set移除元素
//     */
//    public Long sRemove(String key, Object... values) {
//        return stringRedisTemplate.opsForSet().remove(key, values);
//    }
//
//    /**
//     * 移除并返回集合的一个随机元素
//     */
//    public String sPop(String key) {
//        return stringRedisTemplate.opsForSet().pop(key);
//    }
//
//    /**
//     * 将元素value从一个集合移到另一个集合
//     */
//    public Boolean sMove(String key, String value, String destKey) {
//        return stringRedisTemplate.opsForSet().move(key, value, destKey);
//    }
//
//    /**
//     * 获取集合的大小
//     */
//    public Long sSize(String key) {
//        return stringRedisTemplate.opsForSet().size(key);
//    }
//
//    /**
//     * 判断集合是否包含value
//     */
//    public Boolean sIsMember(String key, Object value) {
//        return stringRedisTemplate.opsForSet().isMember(key, value);
//    }
//
//    /**
//     * 获取两个集合的交集
//     */
//    public Set<String> sIntersect(String key, String otherKey) {
//        return stringRedisTemplate.opsForSet().intersect(key, otherKey);
//    }
//
//    /**
//     * 获取key集合与多个集合的交集
//     */
//    public Set<String> sIntersect(String key, Collection<String> otherKeys) {
//        return stringRedisTemplate.opsForSet().intersect(key, otherKeys);
//    }
//
//    /**
//     * key集合与otherKey集合的交集存储到destKey集合中
//     */
//    public Long sIntersectAndStore(String key, String otherKey, String destKey) {
//        return stringRedisTemplate.opsForSet().intersectAndStore(key, otherKey,
//                destKey);
//    }
//
//    /**
//     * key集合与多个集合的交集存储到destKey集合中
//     */
//    public Long sIntersectAndStore(String key, Collection<String> otherKeys,
//                                   String destKey) {
//        return stringRedisTemplate.opsForSet().intersectAndStore(key, otherKeys,
//                destKey);
//    }
//
//    /**
//     * 获取两个集合的并集
//     */
//    public Set<String> sUnion(String key, String otherKeys) {
//        return stringRedisTemplate.opsForSet().union(key, otherKeys);
//    }
//
//    /**
//     * 获取key集合与多个集合的并集
//     */
//    public Set<String> sUnion(String key, Collection<String> otherKeys) {
//        return stringRedisTemplate.opsForSet().union(key, otherKeys);
//    }
//
//    /**
//     * key集合与otherKey集合的并集存储到destKey中
//     */
//    public Long sUnionAndStore(String key, String otherKey, String destKey) {
//        return stringRedisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
//    }
//
//    /**
//     * key集合与多个集合的并集存储到destKey中
//     */
//    public Long sUnionAndStore(String key, Collection<String> otherKeys,
//                               String destKey) {
//        return stringRedisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
//    }
//
//    /**
//     * 获取两个集合的差集
//     */
//    public Set<String> sDifference(String key, String otherKey) {
//        return stringRedisTemplate.opsForSet().difference(key, otherKey);
//    }
//
//    /**
//     * 获取key集合与多个集合的差集
//     */
//    public Set<String> sDifference(String key, Collection<String> otherKeys) {
//        return stringRedisTemplate.opsForSet().difference(key, otherKeys);
//    }
//
//    /**
//     * key集合与otherKey集合的差集存储到destKey中
//     */
//    public Long sDifference(String key, String otherKey, String destKey) {
//        return stringRedisTemplate.opsForSet().differenceAndStore(key, otherKey,
//                destKey);
//    }
//
//    /**
//     * key集合与多个集合的差集存储到destKey中
//     */
//    public Long sDifference(String key, Collection<String> otherKeys,
//                            String destKey) {
//        return stringRedisTemplate.opsForSet().differenceAndStore(key, otherKeys,
//                destKey);
//    }
//
//    /**
//     * 获取集合所有元素
//     */
//    public Set<String> setMembers(String key) {
//        return stringRedisTemplate.opsForSet().members(key);
//    }
//
//    /**
//     * 随机获取集合中的一个元素
//     */
//    public String sRandomMember(String key) {
//        return stringRedisTemplate.opsForSet().randomMember(key);
//    }
//
//    /**
//     * 随机获取集合中count个元素
//     */
//    public List<String> sRandomMembers(String key, long count) {
//        return stringRedisTemplate.opsForSet().randomMembers(key, count);
//    }
//
//    /**
//     * 随机获取集合中count个元素并且去除重复的
//     */
//    public Set<String> sDistinctRandomMembers(String key, long count) {
//        return stringRedisTemplate.opsForSet().distinctRandomMembers(key, count);
//    }
//
//    /**
//     * 使用迭代器获取元素
//     */
//    public Cursor<String> sScan(String key, ScanOptions options) {
//        return stringRedisTemplate.opsForSet().scan(key, options);
//    }
//
//    //******************************************************zSet相关操作
//
//    /**
//     * 添加元素,有序集合是按照元素的score值由小到大排列
//     */
//    public Boolean zAdd(String key, String value, double score) {
//        return stringRedisTemplate.opsForZSet().add(key, value, score);
//    }
//
//    /**
//     * 添加元素,zSet按score由小到大排列
//     */
//    public Long zAdd(String key, Set<TypedTuple<String>> values) {
//        return stringRedisTemplate.opsForZSet().add(key, values);
//    }
//
//    /**
//     * 移除
//     */
//    public Long zRemove(String key, Object... values) {
//        return stringRedisTemplate.opsForZSet().remove(key, values);
//    }
//
//    /**
//     * 增加元素的score值，并返回增加后的值
//     */
//    public Double zIncrementScore(String key, String value, double delta) {
//        return stringRedisTemplate.opsForZSet().incrementScore(key, value, delta);
//    }
//
//    /**
//     * 返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
//     */
//    public Long zRank(String key, Object value) {
//        return stringRedisTemplate.opsForZSet().rank(key, value);
//    }
//
//    /**
//     * 返回元素在集合的排名,按元素的score值由大到小排列
//     */
//    public Long zReverseRank(String key, Object value) {
//        return stringRedisTemplate.opsForZSet().reverseRank(key, value);
//    }
//
//    /**
//     * 获取集合的元素, 从小到大排序
//     */
//    public Set<String> zRange(String key, long start, long end) {
//        return stringRedisTemplate.opsForZSet().range(key, start, end);
//    }
//
//    /**
//     * 获取集合元素, 并且把score值也获取
//     */
//    public Set<TypedTuple<String>> zRangeWithScores(String key, long start, long end) {
//        return stringRedisTemplate.opsForZSet().rangeWithScores(key, start, end);
//    }
//
//    /**
//     * 根据Score值查询集合元素
//     */
//    public Set<String> zRangeByScore(String key, double min, double max) {
//        return stringRedisTemplate.opsForZSet().rangeByScore(key, min, max);
//    }
//
//    /**
//     * 根据Score值查询集合元素, 从小到大排序
//     */
//    public Set<TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max) {
//        return stringRedisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
//    }
//
//    /**
//     * 根据score查询元素,s开始e结束位置
//     */
//    public Set<TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max, long start, long end) {
//        return stringRedisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, start, end);
//    }
//
//    /**
//     * 获取集合的元素, 从大到小排序
//     */
//    public Set<String> zReverseRange(String key, long start, long end) {
//        return stringRedisTemplate.opsForZSet().reverseRange(key, start, end);
//    }
//
//    /**
//     * 获取集合的元素, 从大到小排序, 并返回score值
//     */
//    public Set<TypedTuple<String>> zReverseRangeWithScores(String key, long start, long end) {
//        return stringRedisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
//    }
//
//    /**
//     * 根据Score值查询集合元素, 从大到小排序
//     */
//    public Set<String> zReverseRangeByScore(String key, double min, double max) {
//        return stringRedisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
//    }
//
//    /**
//     * 根据Score值查询集合元素, 从大到小排序
//     */
//    public Set<TypedTuple<String>> zReverseRangeByScoreWithScores(String key, double min, double max) {
//        return stringRedisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
//    }
//
//    /**
//     * 根据score查询,大到小,s开始e结束
//     */
//    public Set<String> zReverseRangeByScore(String key, double min, double max, long start, long end) {
//        return stringRedisTemplate.opsForZSet().reverseRangeByScore(key, min, max, start, end);
//    }
//
//    /**
//     * 根据score值获取集合元素数量
//     */
//    public Long zCount(String key, double min, double max) {
//        return stringRedisTemplate.opsForZSet().count(key, min, max);
//    }
//
//    /**
//     * 获取集合大小
//     */
//    public Long zSize(String key) {
//        return stringRedisTemplate.opsForZSet().size(key);
//    }
//
//    /**
//     * 获取集合大小
//     */
//    public Long zZCard(String key) {
//        return stringRedisTemplate.opsForZSet().zCard(key);
//    }
//
//    /**
//     * 获取集合中value元素的score值
//     */
//    public Double zScore(String key, Object value) {
//        return stringRedisTemplate.opsForZSet().score(key, value);
//    }
//
//    /**
//     * 移除指定索引位置的成员
//     */
//    public Long zRemoveRange(String key, long start, long end) {
//        return stringRedisTemplate.opsForZSet().removeRange(key, start, end);
//    }
//
//    /**
//     * 根据指定的score值的范围来移除成员
//     */
//    public Long zRemoveRangeByScore(String key, double min, double max) {
//        return stringRedisTemplate.opsForZSet().removeRangeByScore(key, min, max);
//    }
//
//    /**
//     * 获取key和otherKey的并集并存储在destKey中
//     */
//    public Long zUnionAndStore(String key, String otherKey, String destKey) {
//        return stringRedisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
//    }
//
//    /**
//     * 获取key和多个集合并集并存在dKey中
//     */
//    public Long zUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
//        return stringRedisTemplate.opsForZSet().unionAndStore(key, otherKeys, destKey);
//    }
//
//    /**
//     * 交集
//     */
//    public Long zIntersectAndStore(String key, String otherKey, String destKey) {
//        return stringRedisTemplate.opsForZSet().intersectAndStore(key, otherKey, destKey);
//    }
//
//    /**
//     * 交集
//     */
//    public Long zIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
//        return stringRedisTemplate.opsForZSet().intersectAndStore(key, otherKeys, destKey);
//    }
//
//    /**
//     * 使用迭代器获取
//     */
//    public Cursor<TypedTuple<String>> zScan(String key, ScanOptions options) {
//        return stringRedisTemplate.opsForZSet().scan(key, options);
//    }
//
//    public int getBusinessCode(String key) {
//        String code = get(key);
//
//        if (StringUtils.isNotEmpty(code)) {
//
//            int maxCode = stringRedisTemplate.opsForValue().increment(key, 1).intValue();
//
//            return maxCode;
//        }
//        return 0;
//    }
//
//    public void setBusinessCode(Integer code, String key) {
//        set(key,String.valueOf(code));
//    }
//}
