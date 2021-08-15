package com.volunteer.gasstation.core;

import com.volunteer.gasstation.api.dto.ApiLoginInfoDTO;

/**
 * @author huoyao
 */
public class SecurityUtil {

    private static final ThreadLocal<ApiLoginInfoDTO> loginInfoCache = new ThreadLocal<ApiLoginInfoDTO>();

    public static void setValue(ApiLoginInfoDTO loginInfo) {
        loginInfoCache.set(loginInfo);
    }

    public static ApiLoginInfoDTO getValue() {
        return loginInfoCache.get();
    }

    public static void remove() {
        loginInfoCache.remove();
    }
}
