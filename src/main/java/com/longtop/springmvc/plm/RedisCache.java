package com.longtop.springmvc.plm;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.DigestUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisCache implements Cache {
	private static Logger logger = LoggerFactory.getLogger(RedisCache.class);

    /** The ReadWriteLock. */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private String id;
    private JedisPool jedisPool;
    private static final int DB_INDEX = 1;
    private final String COMMON_CACHE_KEY = "COM:";
    private static final String UTF8 = "utf-8";

    private ApplicationContext context;

    /**
     * ����һ�������ʶkey
     */
    private String getKey(Object key) {
        StringBuilder accum = new StringBuilder();
        accum.append(COMMON_CACHE_KEY);
        accum.append(this.id).append(":");
//        accum.append(DigestUtils.md5Hex(String.valueOf(key)));
        accum.append(DigestUtils.md5DigestAsHex(String.valueOf(key).getBytes()));
        return accum.toString();
    }

    /**
     * redis key����ǰ׺
     */
    private String getKeys() {
        return COMMON_CACHE_KEY + this.id + ":*";
    }

    public RedisCache() {

    }

    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("���봫��ID");
        }
        context = new ClassPathXmlApplicationContext("spring-redis.xml");
        JedisPoolConfig jedisPoolConfig = (JedisPoolConfig) context.getBean("jedisPoolConfig");
        jedisPool = new JedisPool(jedisPoolConfig, "192.168.12.90", 7504);
        logger.debug(">>>>>>>>>>>>>>>>>>>>>MybatisRedisCache:id=" + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getSize() {
        Jedis jedis = null;
        int result = 0;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DB_INDEX);
            Set<byte[]> keys = jedis.keys(getKeys().getBytes(UTF8));
            if (null != keys && !keys.isEmpty()) {
                result = keys.size();
            }
            logger.debug(this.id + "---->>>>�ܻ�����:" + result);
        } catch (Exception e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
                jedisPool.returnBrokenResource(jedis);
        } finally {
            if (borrowOrOprSuccess)
                jedisPool.returnResource(jedis);
        }
        return result;
    }

    @Override
    public void putObject(Object key, Object value) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DB_INDEX);

            byte[] keys = getKey(key).getBytes(UTF8);
//            jedis.set(keys, SerializeUtil.serialize(value));
            logger.debug("��ӻ���--------" + this.id);
            // getSize();
        } catch (Exception e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
                jedisPool.returnBrokenResource(jedis);
        } finally {
            if (borrowOrOprSuccess)
                jedisPool.returnResource(jedis);
        }
    }

    @Override
    public Object getObject(Object key) {
        Jedis jedis = null;
        Object value = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DB_INDEX);
//            value = SerializeUtil.unserialize(jedis.get(getKey(key).getBytes(UTF8)));
            logger.debug("�ӻ����л�ȡ-----" + this.id);
            // getSize();
        } catch (Exception e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
                jedisPool.returnBrokenResource(jedis);
        } finally {
            if (borrowOrOprSuccess)
                jedisPool.returnResource(jedis);
        }
        return value;
    }

    @Override
    public Object removeObject(Object key) {
        Jedis jedis = null;
        Object value = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DB_INDEX);
            value = jedis.del(getKey(key).getBytes(UTF8));
            logger.debug("LRU�㷨�ӻ������Ƴ�-----" + this.id);
            // getSize();
        } catch (Exception e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
                jedisPool.returnBrokenResource(jedis);
        } finally {
            if (borrowOrOprSuccess)
                jedisPool.returnResource(jedis);
        }
        return value;
    }

    @Override
    public void clear() {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DB_INDEX);
            // �����ɾ����������Ӱ�쵽�������е����ݣ����Ҫ���һ��mapper�Ļ��棨һ��mapper�Ĳ�ͬ���ݲ�����Ӧ��ͬ��key��
            Set<byte[]> keys = jedis.keys(getKeys().getBytes(UTF8));
            logger.debug("����CUD��������ն�ӦMapper����======>" + keys.size());
            for (byte[] key : keys) {
                jedis.del(key);
            }
            // ���������������ķ���������Ľ���ϵͳ���ܣ�û�𵽼��뻺��Ӧ�е����ã����ǲ���ȡ�ġ�
            // jedis.flushDB();
            // jedis.flushAll();
        } catch (Exception e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
                jedisPool.returnBrokenResource(jedis);
        } finally {
            if (borrowOrOprSuccess)
                jedisPool.returnResource(jedis);
        }
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}


