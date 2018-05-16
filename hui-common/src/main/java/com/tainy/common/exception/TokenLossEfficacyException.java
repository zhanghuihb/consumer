package com.tainy.common.exception;

/**
 * @author Tainy
 * @date 2018/5/16 14:44
 */
public class TokenLossEfficacyException extends RuntimeException{

    private int errorCode;

    public TokenLossEfficacyException(String message) {
        super(message);
    }

    public TokenLossEfficacyException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public TokenLossEfficacyException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
