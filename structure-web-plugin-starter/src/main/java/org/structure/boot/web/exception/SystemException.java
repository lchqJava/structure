package org.structure.boot.web.exception;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SystemException extends RuntimeException {
    protected String code;
    protected String msg;
}
