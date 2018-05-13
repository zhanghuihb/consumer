package com.tainy.console.shiro.cache;

import com.tainy.common.util.io.SerializeUtils;
import com.tainy.common.util.redis.RedisUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RedisCache<K, V> implements Cache<K, V> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisCache.class);

	private byte[] cacheName;

	public RedisCache(String cacheName) {
		this.cacheName = cacheName.getBytes();
	}

	@SuppressWarnings("unchecked")
	public V get(K key) throws CacheException {
		LOGGER.debug("get - key={}", key);
		if (key != null) {
			RedisUtils cache = RedisUtils.getInstance();
			ShardedJedis jedis = null;
			try {
				jedis = cache.getJedis();
				return (V) SerializeUtils.deserialize(jedis.hget(this.cacheName, SerializeUtils.serialize(key)));
			} catch (Exception e) {
				e.printStackTrace();
				cache.returnBrokenResource(jedis);
				throw new CacheException();
			} finally {
				cache.returnResource(jedis);
			}
		}
		return null;
	}

	public V put(K key, V value) throws CacheException {
		LOGGER.debug("put - key={}, value={}", key, value);
		if (key != null && value != null) {
			RedisUtils cache = RedisUtils.getInstance();
			ShardedJedis jedis = null;
			try {
				jedis = cache.getJedis();
				jedis.hset(this.cacheName, SerializeUtils.serialize(key), SerializeUtils.serialize(value));
				return value;
			} catch (Exception e) {
				e.printStackTrace();
				cache.returnBrokenResource(jedis);
				throw new CacheException();
			} finally {
				cache.returnResource(jedis);
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public V remove(K key) throws CacheException {
		LOGGER.debug("remove - key={}" + key);
		if (key != null) {
			RedisUtils cache = RedisUtils.getInstance();
			ShardedJedis jedis = null;
			try {
				jedis = cache.getJedis();
				V value = (V) SerializeUtils.deserialize(jedis.hget(this.cacheName, SerializeUtils.serialize(key)));
				jedis.hdel(this.cacheName, SerializeUtils.serialize(key));
				return value;
			} catch (Exception e) {
				e.printStackTrace();
				cache.returnBrokenResource(jedis);
				throw new CacheException();
			} finally {
				cache.returnResource(jedis);
			}
		}
		return null;
	}

	public void clear() throws CacheException {
		LOGGER.debug("clear");
		RedisUtils cache = RedisUtils.getInstance();
		ShardedJedis jedis = null;
		try {
			jedis = cache.getJedis();
			jedis.del(this.cacheName);
		} catch (Exception e) {
			e.printStackTrace();
			cache.returnBrokenResource(jedis);
			throw new CacheException();
		} finally {
			cache.returnResource(jedis);
		}
	}

	public int size() {
		LOGGER.debug("size");
		RedisUtils cache = RedisUtils.getInstance();
		ShardedJedis jedis = null;
		try {
			jedis = cache.getJedis();
			return jedis.hlen(this.cacheName).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			cache.returnBrokenResource(jedis);
		} finally {
			cache.returnResource(jedis);
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public Set<K> keys() {
		LOGGER.debug("keys");
		RedisUtils cache = RedisUtils.getInstance();
		ShardedJedis jedis = null;
		try {
			jedis = cache.getJedis();
			Set<K> keys = new HashSet<K>();
			for (byte[] b : jedis.hkeys(this.cacheName)) {
				keys.add((K) SerializeUtils.deserialize(b));
			}
			return keys;
		} catch (Exception e) {
			e.printStackTrace();
			cache.returnBrokenResource(jedis);
		} finally {
			cache.returnResource(jedis);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Collection<V> values() {
		LOGGER.debug("values");
		RedisUtils cache = RedisUtils.getInstance();
		ShardedJedis jedis = null;
		try {
			jedis = cache.getJedis();
			Collection<V> values = new HashSet<V>();
			for (byte[] b : jedis.hvals(this.cacheName)) {
				values.add((V) SerializeUtils.deserialize(b));
			}
			return values;
		} catch (Exception e) {
			e.printStackTrace();
			cache.returnBrokenResource(jedis);
		} finally {
			cache.returnResource(jedis);
		}
		return null;
	}

}
