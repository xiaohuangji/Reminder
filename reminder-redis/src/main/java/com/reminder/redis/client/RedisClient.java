package com.reminder.redis.client;

import com.reminder.redis.command.*;
import com.reminder.redis.pool.RedisPoolClient;
import com.reminder.redis.serialize.impl.JsonPlainObjectDeserializer;
import com.reminder.redis.serialize.impl.JsonPlainObjectSerializer;

import java.util.*;

public class RedisClient implements IStringCommand,IListCommand,ISetCommand,ISortedSetCommand,IMapCommand{

	private JsonPlainObjectDeserializer jsonDeserializer=new JsonPlainObjectDeserializer();
	
	private  JsonPlainObjectSerializer jsonSerializer=new JsonPlainObjectSerializer();
	
	private  RedisPoolClient redisPoolClient=RedisPoolClient.getInstance();
	
	private String namespace="default";
	
	//这样定义是为了让twemproxy通过{%s}进行shard
    public static final String KEY_TEMPLATE = "%s:{%s}";

    /**
     * 生成带有命名空间的key
     *
     * @param ns  key所在命名空间，避免key之间的冲突
     * @param key 客户端提交的key值，比如uid等等，都转换为字符串类型
     * @return 带有命名空间的key
     */
    public static String combineNsAndKey(String ns, String key) {
        return String.format(KEY_TEMPLATE, ns, key);
    }
	
	public RedisClient(String namespace){
		this.namespace=namespace;
	}
	
	@Override
	public <T> void set(String key, T value) {
		redisPoolClient.set(combineNsAndKey(namespace,key),jsonSerializer.serialize(value));
	}

	@Override
	public <T> void setex(String key, T value, int seconds) {
		redisPoolClient.setex(combineNsAndKey(namespace,key), seconds, jsonSerializer.serialize(value));
	}

	@Override
	public <T> T get(String key, Class<T> clazz) {
		String jsonstr=redisPoolClient.get(combineNsAndKey(namespace, key));
		return jsonDeserializer.deserialize(jsonstr, clazz);
	}

	@Override
	public void del(String key) {
		redisPoolClient.del(combineNsAndKey(namespace, key));
	}

	@Override
	public Boolean exists(String key) {
		return redisPoolClient.exists(combineNsAndKey(namespace, key));
	}

	@Override
	public void expire(String key, int seconds) {
		redisPoolClient.expire(combineNsAndKey(namespace, key), seconds);
	}

	@Override
	public <T> long lpush(String key, T value) {
		return redisPoolClient.lpush(combineNsAndKey(namespace, key),jsonSerializer.serialize(value));
	}

	@Override
	public <T> long rpush(String key, T value) {
		return redisPoolClient.rpush(combineNsAndKey(namespace, key),jsonSerializer.serialize(value));
	}

	@Override
	public long llen(String key) {
		return redisPoolClient.llen(combineNsAndKey(namespace, key));
	}

	@Override
	public <T> List<T> lrange(String key, long start, long offset,Class<T> clazz) {
		List<String> strlist=redisPoolClient.lrange(combineNsAndKey(namespace, key), start, offset);
		return jsonDeserializer.deserialize2List(strlist, clazz);
	}

	@Override
	public <T> Long hset(String key, String mkey, T value) {
		return redisPoolClient.hset(combineNsAndKey(namespace, key), mkey, jsonSerializer.serialize(value));
	}

	@Override
	public String hget(String key, String mkey) {
		return redisPoolClient.hget(combineNsAndKey(namespace, key), mkey);
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		return redisPoolClient.hgetAll(combineNsAndKey(namespace, key));
	}

	@Override
	public Long hlen(String key) {
		return redisPoolClient.hlen(combineNsAndKey(namespace, key));
	}

	@Override
	public <T> void zadd(String key, T value, double score) {
		redisPoolClient.zadd(combineNsAndKey(namespace, key), score, jsonSerializer.serialize(value));
	}

	@Override
	public <T> void zadd(String key, Map<Double,T> map) {
		Map<Double, String> zmap=new HashMap<Double, String>();
		Iterator iter = map.entrySet().iterator(); 
		while (iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    String value = jsonSerializer.serialize((T)entry.getValue()); 
		    Double mkey =(Double)entry.getKey();
		    zmap.put(mkey, value);
		}
		redisPoolClient.zadd(combineNsAndKey(namespace, key), zmap);
	}

	@Override
	public Long zcard(String key) {
		return redisPoolClient.zcard(combineNsAndKey(namespace, key));
	}

	@Override
	public <T> Set<T> zrange(String key, long start, long offset,Class<T> clazz) {
		Set<String> set= redisPoolClient.zrange(combineNsAndKey(namespace, key), start, offset);
		return jsonDeserializer.deserialize2Set(set, clazz);
	}

	@Override
	public <T> Set<T> zrevrange(String key, long start, long offset,Class<T> clazz) {
		Set<String> set= redisPoolClient.zrevrange(combineNsAndKey(namespace, key), start, offset);
		return jsonDeserializer.deserialize2Set(set, clazz);
	}

	@Override
	public <T> Set<T> zrangeByScore(String key, double min, double max,Class<T> clazz) {
		Set<String> set= redisPoolClient.zrangeByScore(combineNsAndKey(namespace, key), min, max);
		return jsonDeserializer.deserialize2Set(set, clazz);
	}

	@Override
	public <T> Set<T> zrevrangeByScore(String key, double min, double max,Class<T> clazz) {
		Set<String> set= redisPoolClient.zrevrangeByScore(combineNsAndKey(namespace, key), min, max);
		return jsonDeserializer.deserialize2Set(set, clazz);
	}

	@Override
	public <T> void sadd(String key, T value) {
		redisPoolClient.sadd(combineNsAndKey(namespace, key),jsonSerializer.serialize(value));
	}

	@Override
	public Long scard(String key) {
		return redisPoolClient.scard(combineNsAndKey(namespace, key));
	}

	public <T> Set<T> smember(String key ,Class<T> clazz){
		Set<String> set=redisPoolClient.smembers(combineNsAndKey(namespace, key));
		return jsonDeserializer.deserialize2Set(set, clazz);
	}
	@Override
	public <T> long lpush(String key, List<T> values) {
		String[] s = new String[values.size()];
		return redisPoolClient.lpush(combineNsAndKey(namespace,key), jsonSerializer.serialize(values).toArray(s));
	}

	@Override
	public <T> long rpush(String key, List<T> values) {
		String[] s = new String[values.size()];
		return redisPoolClient.rpush(combineNsAndKey(namespace,key), jsonSerializer.serialize(values).toArray(s));
	}

	@Override
	public <T> Long zrem(String key, Set<T> values) {
		String[] s = new String[values.size()];
		return redisPoolClient.zrem(combineNsAndKey(namespace,key), jsonSerializer.serialize(values).toArray(s));
	}

	@Override
	public <T> void sadd(String key, Set<T> values) {
		String[] s = new String[values.size()];
		redisPoolClient.sadd(combineNsAndKey(namespace,key), jsonSerializer.serialize(values).toArray(s));
	}

	@Override
	public <T> Long srem(String key, Set<T> values) {
		String[] s = new String[values.size()];
		return redisPoolClient.srem(combineNsAndKey(namespace,key), jsonSerializer.serialize(values).toArray(s));
	}

	@Override
	public Long hdel(String key, String hkey) {
		return redisPoolClient.hdel(combineNsAndKey(namespace,key), hkey);
	}

	@Override
	public String hmset(String key ,Map<String,Object> map) {
		Map<String, String> zmap=new HashMap<String, String>();
		Iterator iter = map.entrySet().iterator(); 
		while (iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    String mkey =(String)entry.getKey();
		    String value = jsonSerializer.serialize(entry.getValue());
		    zmap.put(mkey, value);
		}
		return redisPoolClient.hmset(combineNsAndKey(namespace,key), zmap);
	}

	@Override
	public Long hincrBy(String key, String hkey,long num) {
		return redisPoolClient.hincrBy(combineNsAndKey(namespace,key),hkey,num);
	}

	@Override
	public <T> Long lrem(String key, long count, T value) {
		return redisPoolClient.lrem(combineNsAndKey(namespace,key), count, jsonSerializer.serialize(value));
	}

	@Override
	public <T> long lpushx(String key, T value) {
		return redisPoolClient.lpushx(combineNsAndKey(namespace, key),jsonSerializer.serialize(value));
	}


	@Override
	public <T> long rpushx(String key, T value) {
		return redisPoolClient.rpushx(combineNsAndKey(namespace, key),jsonSerializer.serialize(value));
	}

	@Override
	public Boolean hexists(String key, String hkey) {
		return redisPoolClient.hexists(combineNsAndKey(namespace, key), hkey);
	}

	@Override
	public <T> List<T> mget(List<String> keys, Class<T> clazz) {
		List<String> realKeyList=new ArrayList<String>();
		for(String key:keys){
			realKeyList.add(combineNsAndKey(namespace, key));
		}
		String[] s = new String[realKeyList.size()];
		List<String> rlist=redisPoolClient.mget(realKeyList.toArray(s));
		
		return jsonDeserializer.deserialize2List(rlist,clazz);
	}

    public static void main(String[] args) {
        RedisClient s=new RedisClient("test");
//        s.set("ss",s);
    }

}
