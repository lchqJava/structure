package org.structure.boot.redis.lock;

/**
 * @version V1.0.0
 * @Title: DistributedLock
 * @Package org.structure.boot.redis.lock
 * @Description: redis
 * @author: chuck
 * @date: 2020/6/4 11:48
 */
public interface DistributedLock {
     static final long TIMEOUT_MILLIS = 30000;
     static final int RETRY_TIMES = Integer.MAX_VALUE;
     static final long SLEEP_MILLIS = 500;
     default boolean lock(String key){
          return lock(key, TIMEOUT_MILLIS, RETRY_TIMES, SLEEP_MILLIS);
     }
     default boolean lock(String key, int retryTimes){
          return lock(key, TIMEOUT_MILLIS, retryTimes, SLEEP_MILLIS);
     }
     default boolean lock(String key, int retryTimes, long sleepMillis){
          return lock(key, TIMEOUT_MILLIS, retryTimes, sleepMillis);
     }
     default boolean lock(String key, long expire){
          return lock(key, expire, RETRY_TIMES, SLEEP_MILLIS);
     }
     default boolean lock(String key, long expire, int retryTimes){
          return lock(key, expire, retryTimes, SLEEP_MILLIS);
     }
     boolean lock(String key, long expire, int retryTimes, long sleepMillis);
     boolean releaseLock(String key);
}
