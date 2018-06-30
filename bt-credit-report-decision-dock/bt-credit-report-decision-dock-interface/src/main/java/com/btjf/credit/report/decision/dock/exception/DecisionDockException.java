package com.btjf.credit.report.decision.dock.exception;

import com.btjf.common.exception.BTException;

/**
 * Created by zsw on 2017/6/14.
 *
 * @Description:
 */
public class DecisionDockException extends BTException {
    /**
     * 异常
     */
    public DecisionDockException() {
        super();
    }

    /**
     * @param message
     *            异常消息
     * @param cause
     *            异常原因
     */
    public DecisionDockException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     *            异常消息
     */
    public DecisionDockException(String message) {
        super(message);
    }

    /**
     * @param cause
     *            异常原因
     */
    public DecisionDockException(Throwable cause) {
        super(cause);
    }
}
