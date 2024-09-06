//package com.loanmanagement;
//
//public class LoanManagementException extends Exception {
//    public LoanManagementException(String message) {
//        super(message);
//    }
//
//    public LoanManagementException(String message, Throwable cause) {
//        super(message, cause);
//    }
//}

package com.loanmanagement;

public class LoanManagementException extends Exception {
    private static final long serialVersionUID = 1L;

    public LoanManagementException(String message) {
        super(message);
    }

    public LoanManagementException(String message, Throwable cause) {
        super(message, cause);
    }
}
