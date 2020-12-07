package org.structure.boot.common.entity;

import lombok.*;
import org.structure.boot.common.enums.ResultCodeEnum;
import org.structure.boot.common.exception.CommonException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Title: ResultVo
 * @Package com.structure.boot.common.object.vo
 * @Description: 返回结果封装VO
 * @author: lcq
 * @date: 2019/1/14 17:29
 * @Version V1.0.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> implements Serializable {
    private String code;
    private String msg;
    private String subCode;
    private String subMsg;
    private long timestamp;
    private T data;

    public static <T> ResultVO<T> success(T d) {
        return builder(d).code(ResultCodeEnum.SUCCESS.getCode()).msg(ResultCodeEnum.SUCCESS.getMsg()).subCode(ResultCodeEnum.SUCCESS.getCode()).timestamp((new Date()).getTime()).build();
    }

    public static ResultVO exception(String message) {
        return builder().code(ResultCodeEnum.ERR.getCode()).msg(message).timestamp((new Date()).getTime()).build();
    }

    public static ResultVO exception(CommonException ce) {
        return builder().code(ResultCodeEnum.ERR.getCode()).msg(ce.getMessage()).subCode(ce.getCode()).subMsg(ce.getMsg()).timestamp((new Date()).getTime()).build();
    }

    public static ResultVO exception(String msg, String subCode, String subMsg) {
        return builder().code(ResultCodeEnum.ERR.getCode()).msg(msg).subCode(subCode).subMsg(subMsg).timestamp((new Date()).getTime()).build();
    }

    public static ResultVO fail(String subMsg) {
        return builder().code(ResultCodeEnum.SUCCESS.getCode()).msg(ResultCodeEnum.SUCCESS.getMsg()).subCode(ResultCodeEnum.FAIL.getCode()).subMsg(subMsg).timestamp((new Date()).getTime()).build();
    }

    public static ResultVO fail(String subCode, String subMsg) {
        return builder().code(ResultCodeEnum.SUCCESS.getCode()).msg(ResultCodeEnum.SUCCESS.getMsg()).subCode(subCode).subMsg(subMsg).timestamp((new Date()).getTime()).data(null).build();
    }

    public static ResultVO fail(Integer subCode, String subMsg) {
        return builder().code(ResultCodeEnum.SUCCESS.getCode()).msg(ResultCodeEnum.SUCCESS.getMsg()).subCode(subCode + "").subMsg(subMsg).timestamp((new Date()).getTime()).data(null).build();
    }

    public static ResultVO fail(String subMsg, Object data) {
        return builder().code(ResultCodeEnum.SUCCESS.getCode()).msg(ResultCodeEnum.SUCCESS.getMsg()).subCode(ResultCodeEnum.FAIL.getCode()).subMsg(subMsg).timestamp((new Date()).getTime()).data(data).build();
    }

    public static <T> ResultVO<T> fail(String subCode, String subMsg, T d) {
        return builder(d).code(ResultCodeEnum.SUCCESS.getCode()).msg(ResultCodeEnum.SUCCESS.getMsg()).subCode(ResultCodeEnum.FAIL.getCode()).subMsg(subMsg).timestamp((new Date()).getTime()).data(d).build();
    }

    public static ResultVO fallback(String subCode, String subMsg) {
        return builder().code(ResultCodeEnum.FALLBACK.getCode()).msg(ResultCodeEnum.FALLBACK.getMsg()).subCode(subCode).subMsg(subMsg).timestamp((new Date()).getTime()).build();
    }

    public static ResultVO unauthorized(String subCode, String subMsg) {
        return builder().code(ResultCodeEnum.UNAUTHORIZED.getCode()).msg(ResultCodeEnum.UNAUTHORIZED.getMsg()).subCode(subCode).subMsg(subMsg).timestamp((new Date()).getTime()).build();
    }

    public static ResultVO verificationFailed(List<VerificationFailedMsg> verificationFailedMsgList) {
        return builder().code(ResultCodeEnum.SUCCESS.getCode()).msg(ResultCodeEnum.SUCCESS.getMsg()).subCode(ResultCodeEnum.VERIFICATION_FAILED.getCode()).subMsg(ResultCodeEnum.VERIFICATION_FAILED.getMsg()).timestamp((new Date()).getTime()).data(verificationFailedMsgList).build();
    }

    public static ResultVO notfound() {
        return builder().code(ResultCodeEnum.FALLBACK.getCode()).msg(ResultCodeEnum.FALLBACK.getMsg()).subCode(ResultCodeEnum.NOT_FOUND.getCode()).subMsg(ResultCodeEnum.NOT_FOUND.getMsg()).timestamp((new Date()).getTime()).build();
    }

    public boolean isSuccess() {
        return this.code.equals(ResultCodeEnum.SUCCESS.getCode()) && this.subCode.equals(ResultCodeEnum.SUCCESS.getCode());
    }

    public boolean isFail() {
        return !this.isSuccess();
    }

    public static boolean isSuccess(ResultVO r) {
        return r.code.equals(ResultCodeEnum.SUCCESS.getCode()) && r.subCode.equals(ResultCodeEnum.SUCCESS.getCode());
    }

    public static boolean isFail(ResultVO r) {
        return !isSuccess(r);
    }

    public static <T> ResultBuilder<T> builder() {
        return new ResultBuilder<T>();
    }

    public static <T> ResultBuilder<T> builder(T t) {
        return new ResultBuilder<T>(t);
    }

    protected boolean canEqual(Object other) {
        return other instanceof ResultVO;
    }

    public static class ResultBuilder<T> {
        private String code;
        private String msg;
        private String subCode;
        private String subMsg;
        private long timestamp;
        private T data;

        ResultBuilder() {
        }

        ResultBuilder(T t) {
            this.data = t;
        }

        public ResultBuilder<T> code(String code) {
            this.code = code;
            return this;
        }

        public ResultBuilder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public ResultBuilder<T> subCode(String subCode) {
            this.subCode = subCode;
            return this;
        }

        public ResultBuilder<T> subMsg(String subMsg) {
            this.subMsg = subMsg;
            return this;
        }

        public ResultBuilder<T> timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ResultBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ResultVO<T> build() {
            return new ResultVO(this.code, this.msg, this.subCode, this.subMsg, this.timestamp, this.data);
        }
    }

}

