package org.structure.boot.redisson.exception;

/**
 *
 * @Title:  LockException
 * @Package com.mogu.redisson.exception
 * @Description: 分布式锁异常
 * @author: qjx
 * @date:   2019/9/3 14:55
 * @Version V1.0.0
 *
 */
public class LockException extends RuntimeException{
    public LockException() {
    }

    public LockException(String message) {
        super(message);
    }

    public LockException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockException(Throwable cause) {
        super(cause);
    }

    public LockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
