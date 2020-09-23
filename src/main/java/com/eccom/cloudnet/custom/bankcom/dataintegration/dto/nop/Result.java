package com.eccom.cloudnet.custom.bankcom.dataintegration.dto.nop;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Result<T> {
    public static final int SUCCESS_CODE = 0;
    public static final int FAILED_CODE = -1;

    public static final String ERROR_FOREIGN_KEY = "foreignKey";
    public static final String ERROR_EXIST = "exist";
    public static final String ERROR_NOT_EXIST = "not-exist";
    public static final String ERROR_PARAMETER = "parameter";
    public static final String ERROR_REFUSED = "refused";

    public static final Result<Void> SUCCESS = Result.<Void>builder().code(SUCCESS_CODE).build();

    private int code;
    private T res;
    private String error;
    private String trace;
}
