package org.structure.boot.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.structure.boot.common.entity.ResultVO;
import org.structure.boot.common.enums.ErrorCodeEnum;
import org.structure.boot.common.exception.CommonException;
import org.structure.boot.web.exception.SystemException;
import org.structure.boot.web.exception.ThirdPartyException;


/**
 * @Title: MyControllerAdvice
 * @Package com.structure.boot.starter.web.config
 * @Description: 全局异常处理
 * @author: lcq
 * @date: 2019/3/25 19:18
 * @Version V1.0.0
 */
@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultVO exceptionHandler(Exception ex) {
        log.error("全局未知异常捕获======》", ex);
        return ResultVO.exception(ErrorCodeEnum.SYSTEM_ERROR.getErrorType(), "500", ex.toString());
    }

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public ResultVO errorHandler(Throwable ex) {
        log.error("全局未知异常捕获======》", ex);
        return ResultVO.exception(ErrorCodeEnum.SYSTEM_ERROR.getErrorType(), "500", ex.toString());
    }

    /**
     * 业务自定义异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = CommonException.class)
    public ResultVO structureErrorHandler(CommonException ex) {
        log.error("全局业务异常捕捉======》", ex);
        return ResultVO.exception(ErrorCodeEnum.LOGIC_ERROR.getErrorType(), ex.getCode(), ex.getMsg());
    }

    /**
     * 第三方异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ThirdPartyException.class)
    public ResultVO thirdPartyErrorHandler(ThirdPartyException ex) {
        log.error("全局第三方异常捕捉======》", ex);
        return ResultVO.exception(ErrorCodeEnum.THIRD_PARTY_ERROR.getErrorType(), ex.getCode(), ex.getMsg());
    }

    /**
     * 系统异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = SystemException.class)
    public ResultVO systemErrorHandler(SystemException ex) {
        log.error("全局系统异常捕捉======》", ex);
        return ResultVO.exception(ErrorCodeEnum.SYSTEM_ERROR.getErrorType(), ex.getCode(), ex.getMsg());
    }
}
