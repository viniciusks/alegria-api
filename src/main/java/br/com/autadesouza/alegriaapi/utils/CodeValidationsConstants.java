package br.com.autadesouza.alegriaapi.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeValidationsConstants {

    public static final String UNMAPPED_ERROR = "40099";
    public static final String FORBIDDEN = "40301";
    public static final String NOT_FOUND = "40401";
    public static final String INVALID_PARAMETER = "40001";
    public static final String REQUIRED_PARAMETER = "40002";
    public static final String UNABLE_TO_CREATE = "42001";
    public static final String METHOD_NOT_ALLOWED = "40501";
    public static final String UNSUPPORTED_MEDIA_TYPE = "41501";
    public static final String INTERNAL_SERVER_ERROR = "50001";
    public static final String CONFLICT_IDEMPOTENCY = "40005";

}
