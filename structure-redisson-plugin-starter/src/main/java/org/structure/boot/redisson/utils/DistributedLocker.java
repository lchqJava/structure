package org.structure.boot.redisson.utils;

/**
 *
 * @Title:  DistributedLocker
 * @Description: 分布式锁接口
 * @date:   2019/9/4 12:41
 * @Version V1.0.0
 *
 */
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

public interface DistributedLocker {

    RLock lock(String lockKey);

    RLock lock(String lockKey, int timeout);

    RLock lock(String lockKey, TimeUnit unit, int timeout);

    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);

    void unlock(String lockKey);

    void unlock(RLock lock);
}