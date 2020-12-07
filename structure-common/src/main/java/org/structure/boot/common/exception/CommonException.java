package org.structure.boot.common.exception;

import lombok.*;

/**
 * @author CHUCK
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommonException extends RuntimeException {

    protected String code;

    protected String msg;
}
