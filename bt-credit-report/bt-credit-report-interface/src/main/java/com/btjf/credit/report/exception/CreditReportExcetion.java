package com.btjf.credit.report.exception;

import com.btjf.common.exception.BTException;

/**
 * Created by zsw on 2017/6/14.
 *
 * @Description:
 */
public class CreditReportExcetion extends BTException {
        /**
         * 异常
         */
        public CreditReportExcetion() {
            super();
        }

        /**
         * @param message
         *            异常消息
         * @param cause
         *            异常原因
         */
        public CreditReportExcetion(String message, Throwable cause) {
            super(message, cause);
        }

        /**
         * @param message
         *            异常消息
         */
        public CreditReportExcetion(String message) {
            super(message);
        }

        /**
         * @param message
         *            异常消息
         */
        public CreditReportExcetion(Integer message) {
            super(String.valueOf(message));
        }

        /**
         * @param cause
         *            异常原因
         */
        public CreditReportExcetion(Throwable cause) {
            super(cause);
        }
}
