package com.oio.practice.jdk.enums;

/**
 * @author Liqc
 * @date 2020/3/25 10:31
 */
public enum ErrorCodeEnum implements ErrorCode {
    ;

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
